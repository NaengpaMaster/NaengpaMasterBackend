package com.naengpa.naengpamasterbackend.admin.service;

import com.naengpa.naengpamasterbackend.admin.dto.request.AdminAnswerRequest;
import com.naengpa.naengpamasterbackend.admin.dto.response.AdminInquiryDetailResponse;
import com.naengpa.naengpamasterbackend.admin.dto.response.AdminInquiryResponse;
import com.naengpa.naengpamasterbackend.admin.repository.AdminInquiryAnswerRepository;
import com.naengpa.naengpamasterbackend.admin.repository.AdminInquiryRepository;
import com.naengpa.naengpamasterbackend.global.exception.InquiryAnswerNotFoundException;
import com.naengpa.naengpamasterbackend.global.exception.InquiryNotFoundException;
import com.naengpa.naengpamasterbackend.global.exception.MemberNotFoundException;
import com.naengpa.naengpamasterbackend.inquiry.entity.Inquiry;
import com.naengpa.naengpamasterbackend.inquiry.entity.InquiryAnswer;
import com.naengpa.naengpamasterbackend.member.entity.Member;
import com.naengpa.naengpamasterbackend.member.repository.MemberRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class AdminInquiryService {

    private final AdminInquiryRepository adminInquiryRepository;
    private final AdminInquiryAnswerRepository adminInquiryAnswerRepository;
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public Page<AdminInquiryResponse> getInquiries(Boolean isAnswered, Pageable pageable) {
        if (isAnswered == null) {
            return adminInquiryRepository.findByIsDeleted(false, pageable).map(inquiry -> {
                Member member = memberRepository.findById(inquiry.getMemberId()).orElse(null);
                String nickname = member != null ? member.getNickname() : null;
                return AdminInquiryResponse.from(inquiry, nickname);
            });
        }

        return adminInquiryRepository.findByIsAnsweredAndIsDeleted(isAnswered, false, pageable)
                .map(inquiry -> {
                    Member member = memberRepository.findById(inquiry.getMemberId()).orElse(null);
                    String nickname = member != null ? member.getNickname() : null;
                    return AdminInquiryResponse.from(inquiry, nickname);
                });
    }

    @Transactional(readOnly = true)
    public AdminInquiryDetailResponse getInquiryDetail(Long inquiryId) {
        Inquiry inquiry = adminInquiryRepository.findById(inquiryId)
                .orElseThrow(InquiryNotFoundException::new);

        InquiryAnswer inquiryAnswer = adminInquiryAnswerRepository.findByInquiryId(inquiryId).orElse(null);

        Member member = memberRepository.findById(inquiry.getMemberId()).orElse(null);
        String nickname = member != null ? member.getNickname() : null;
        return AdminInquiryDetailResponse.from(inquiry, inquiryAnswer, nickname);
    }

    @Transactional
    public void createInquiryAnswer(Long inquiryId, AdminAnswerRequest request, String email) {
        Inquiry inquiry = adminInquiryRepository.findById(inquiryId)
                .orElseThrow(InquiryNotFoundException::new);

        Long adminId = resolveAdminIdOrThrow(email);

        InquiryAnswer inquiryAnswer = InquiryAnswer.create(inquiryId, request.content(), adminId);
        adminInquiryAnswerRepository.save(inquiryAnswer);
        inquiry.markAsAnswered();
    }

    @Transactional
    public void updateInquiryAnswer(Long inquiryId, Long answerId, AdminAnswerRequest request, String email) {
        InquiryAnswer inquiryAnswer = adminInquiryAnswerRepository.findById(answerId)
                .orElseThrow(InquiryAnswerNotFoundException::new);

        Long adminId = resolveAdminIdOrThrow(email);

        Inquiry inquiry = adminInquiryRepository.findById(inquiryId).orElseThrow(InquiryNotFoundException::new);

        if (!inquiryAnswer.getInquiryId().equals(inquiry.getId())) {
            throw new InquiryNotFoundException();
        }

        InquiryAnswer.update(inquiryAnswer, request.content(), adminId);
    }

    @Transactional
    public void deleteInquiryAnswer(Long inquiryId, Long answerId) {
        Inquiry inquiry = adminInquiryRepository.findById(inquiryId).orElseThrow(InquiryNotFoundException::new);
        InquiryAnswer inquiryAnswer = adminInquiryAnswerRepository.findById(answerId).orElseThrow(InquiryAnswerNotFoundException::new);

        if (!inquiryAnswer.getInquiryId().equals(inquiry.getId())) {
            throw new InquiryNotFoundException();
        }

        InquiryAnswer.delete(inquiryAnswer);
        inquiry.markAsUnanswered();
    }

    private Long resolveAdminIdOrThrow(String email) {
        if (!StringUtils.hasText(email)) {
            throw new MemberNotFoundException();
        }
        return memberRepository.findByEmail(email)
                .map(Member::getId)
                .orElseThrow(MemberNotFoundException::new);
    }

}