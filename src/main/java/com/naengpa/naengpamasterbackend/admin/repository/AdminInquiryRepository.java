package com.naengpa.naengpamasterbackend.admin.repository;

import com.naengpa.naengpamasterbackend.inquiry.entity.Inquiry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminInquiryRepository extends JpaRepository<Inquiry, Long> {

    @Query("SELECT COUNT(i) FROM Inquiry i WHERE i.isAnswered = false AND i.isDeleted = false")
    Long countPendingInquiries();

    Page<Inquiry> findByIsAnsweredAndIsDeletedFalseOrderByCreatedAtDesc(Boolean isAnswered, Pageable pageable);

    Page<Inquiry> findByIsAnsweredAndIsDeletedFalseOrderByCreatedAtAsc(Boolean isAnswered, Pageable pageable);

    Optional<Inquiry> findByIdAndIsDeletedFalse(Long inquiryId);
}
