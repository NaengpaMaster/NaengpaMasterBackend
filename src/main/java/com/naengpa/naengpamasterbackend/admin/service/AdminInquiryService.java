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
import com.naengpa.naengpamasterbackend.notification.service.NotificationService;
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
    private final NotificationService notificationService;

    @Transactional(readOnly = true)
    public Page<AdminInquiryResponse> getInquiries(Boolean isAnswered, Pageable pageable) {
        if (isAnswered) {
            return adminInquiryRepository.findByIsAnsweredAndIsDeletedFalseOrderByAnsweredAtDesc(isAnswered, pageable)
                    .map(inquiry -> {
                        Member member = memberRepository.findById(inquiry.getMemberId()).orElse(null);
                        String nickname = member != null ? member.getNickname() : null;
                        return AdminInquiryResponse.from(inquiry, nickname);
                    });
        }

        return adminInquiryRepository.findByIsAnsweredAndIsDeletedFalseOrderByCreatedAtAsc(isAnswered, pageable)
                .map(inquiry -> {
                    Member member = memberRepository.findById(inquiry.getMemberId()).orElse(null);
                    String nickname = member != null ? member.getNickname() : null;
                    return AdminInquiryResponse.from(inquiry, nickname);
                });
    }

    @Transactional(readOnly = true)
    public AdminInquiryDetailResponse getInquiryDetail(Long inquiryId) {
        Inquiry inquiry = adminInquiryRepository.findByIdAndIsDeletedFalse(inquiryId)
                .orElseThrow(InquiryNotFoundException::new);

        InquiryAnswer inquiryAnswer = adminInquiryAnswerRepository.findByInquiryIdAndIsDeletedFalse(inquiryId).orElse(null);

        Member member = memberRepository.findById(inquiry.getMemberId()).orElse(null);
        String nickname = member != null ? member.getNickname() : null;
        return AdminInquiryDetailResponse.from(inquiry, inquiryAnswer, nickname);
    }

    @Transactional
    public void createInquiryAnswer(Long inquiryId, AdminAnswerRequest request, String email) {
        Inquiry inquiry = adminInquiryRepository.findByIdAndIsDeletedFalse(inquiryId)
                .orElseThrow(InquiryNotFoundException::new);

        Long adminId = resolveAdminIdOrThrow(email);

        InquiryAnswer inquiryAnswer = InquiryAnswer.create(inquiryId, request.content(), adminId);
        adminInquiryAnswerRepository.save(inquiryAnswer);
        inquiry.markAsAnswered();
        notificationService.createInquiryAnsweredNotification(inquiry.getMemberId(), inquiry.getId());
    }

    @Transactional
    public void updateInquiryAnswer(Long inquiryId, Long answerId, AdminAnswerRequest request, String email) {
        InquiryAnswer inquiryAnswer = adminInquiryAnswerRepository.findById(answerId)
                .orElseThrow(InquiryAnswerNotFoundException::new);

        Inquiry inquiry = adminInquiryRepository.findByIdAndIsDeletedFalse(inquiryId).orElseThrow(InquiryNotFoundException::new);

        Long adminId = resolveAdminIdOrThrow(email);

        if (!inquiryAnswer.getInquiryId().equals(inquiry.getId())) {
            throw new InquiryNotFoundException();
        }

        inquiryAnswer.update(request.content(), adminId);
    }

    @Transactional
    public void deleteInquiryAnswer(Long inquiryId, Long answerId) {
        Inquiry inquiry = adminInquiryRepository.findByIdAndIsDeletedFalse(inquiryId).orElseThrow(InquiryNotFoundException::new);
        InquiryAnswer inquiryAnswer = adminInquiryAnswerRepository.findById(answerId).orElseThrow(InquiryAnswerNotFoundException::new);

        if (!inquiryAnswer.getInquiryId().equals(inquiry.getId())) {
            throw new InquiryNotFoundException();
        }

        inquiryAnswer.delete();
        inquiry.markAsUnanswered();
    }

    @Transactional
    public void deleteInquiry(Long inquiryId) {
        Inquiry inquiry = adminInquiryRepository.findByIdAndIsDeletedFalse(inquiryId)
                .orElseThrow(InquiryNotFoundException::new);

        adminInquiryAnswerRepository.findByInquiryId(inquiryId)
                .ifPresent(InquiryAnswer::delete);

        inquiry.delete();
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
