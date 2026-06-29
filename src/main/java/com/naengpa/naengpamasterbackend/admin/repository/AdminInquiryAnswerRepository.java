package com.naengpa.naengpamasterbackend.admin.repository;

import com.naengpa.naengpamasterbackend.inquiry.entity.InquiryAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminInquiryAnswerRepository extends JpaRepository<InquiryAnswer, Long> {

}
