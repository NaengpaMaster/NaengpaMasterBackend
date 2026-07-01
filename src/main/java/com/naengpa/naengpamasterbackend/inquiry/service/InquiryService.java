package com.naengpa.naengpamasterbackend.inquiry.service;

import com.naengpa.naengpamasterbackend.inquiry.dto.request.InquiryRequest;
import com.naengpa.naengpamasterbackend.inquiry.dto.response.InquiryDetailResponse;
import com.naengpa.naengpamasterbackend.inquiry.dto.response.InquiryResponse;
import com.naengpa.naengpamasterbackend.inquiry.entity.Inquiry;
import com.naengpa.naengpamasterbackend.inquiry.entity.InquiryAnswer;
import com.naengpa.naengpamasterbackend.inquiry.repository.InquiryAnswerRepository;
import com.naengpa.naengpamasterbackend.inquiry.repository.InquiryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InquiryService {

    private final InquiryRepository inquiryRepository;
    private final InquiryAnswerRepository inquiryAnswerRepository;

    // 목록 조회
    @Transactional(readOnly = true)
    public Page<InquiryResponse> getInquiries(Long memberId, Boolean isDeleted, int page, int size) {
        return inquiryRepository.findByMemberIdAndIsDeletedFalse(memberId, isDeleted, PageRequest.of(page, size))
                .map(InquiryResponse::from);
    }

    // 상세 조회
    @Transactional(readOnly = true)
    public InquiryDetailResponse getInquiryDetail(Long inquiryId) {
        Inquiry inquiry = inquiryRepository.findById(inquiryId).orElseThrow(InquiryNotFoundException::new);
        InquiryAnswer inquiryAnswer = inquiryAnswerRepository.findByInquiryId(inquiryId).orElse(null);
        return InquiryDetailResponse.from(inquiry ,inquiryAnswer);
    }

    // 등록
    @Transactional
    public void createInquiry(InquiryRequest request, String email) {
        Inquiry inquiry = Inquiry.create(request);
        inquiryRepository.save(inquiry);
    }

    // 수정
    @Transactional
    public void updateInquiry(Long inquiryId, InquiryRequest request, String email) {
        Inquiry inquiry = inquiryRepository.findById(inquiryId).orElseThrow(InquiryNotFoundException::new);
        Inquiry.update(inquiry, request);
    }

    // 삭제
    @Transactional
    public void deleteInquiry(Long inquiryId) {
        Inquiry inquiry = inquiryRepository.findById(inquiryId).orElseThrow(InquiryNotFoundException::new);


    }

}
