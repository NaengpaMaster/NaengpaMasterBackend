package com.naengpa.naengpamasterbackend.admin.repository;

import com.naengpa.naengpamasterbackend.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminMemberRepository extends JpaRepository<Member, Long> {

}
