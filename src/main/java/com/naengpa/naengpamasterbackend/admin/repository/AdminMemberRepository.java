package com.naengpa.naengpamasterbackend.admin.repository;

import com.naengpa.naengpamasterbackend.member.entity.Member;
import com.naengpa.naengpamasterbackend.member.entity.MemberRole;
import com.naengpa.naengpamasterbackend.member.entity.MemberStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminMemberRepository extends JpaRepository<Member, Long> {

    @Query("SELECT m " +
            "FROM Member m " +
            "WHERE m.role = :role AND " +
            "m.status = :status AND " +
            "(:search IS NULL OR m.nickname LIKE %:search% OR m.email LIKE %:search%)")
    Page<Member> findMembers(MemberRole role, MemberStatus status, String search, Pageable pageable);


}
