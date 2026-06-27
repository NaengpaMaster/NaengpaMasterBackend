package com.naengpa.naengpamasterbackend.admin.service;

import com.naengpa.naengpamasterbackend.admin.dto.response.AdminMemberResponse;
import com.naengpa.naengpamasterbackend.admin.repository.AdminMemberRepository;
import com.naengpa.naengpamasterbackend.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminMemberService {

    private final AdminMemberRepository adminMemberRepository;

    @Transactional(readOnly = true)
    public Page<AdminMemberResponse> getMembers(Pageable pageable) {
        Page<Member> members = adminMemberRepository.findAll(pageable);
        return members.map(AdminMemberResponse::from);
    }
}
