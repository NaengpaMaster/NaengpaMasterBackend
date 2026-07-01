package com.naengpa.naengpamasterbackend.inquiry.repository;

import com.naengpa.naengpamasterbackend.inquiry.entity.Inquiry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InquiryRepository extends JpaRepository<Inquiry, Long> {

    Page<Inquiry> findByMemberIdAndIsDeletedFalse(Long memberId, Boolean isDeleted, Pageable pageable);
}
