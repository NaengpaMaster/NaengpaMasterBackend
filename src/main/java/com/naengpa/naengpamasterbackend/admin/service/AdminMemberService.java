package com.naengpa.naengpamasterbackend.admin.service;

import com.naengpa.naengpamasterbackend.admin.dto.request.AdminMemberRoleRequest;
import com.naengpa.naengpamasterbackend.admin.dto.request.AdminMemberStatusRequest;
import com.naengpa.naengpamasterbackend.admin.dto.response.AdminMemberResponse;
import com.naengpa.naengpamasterbackend.admin.repository.AdminMemberRepository;
import com.naengpa.naengpamasterbackend.global.exception.MemberNotFoundException;
import com.naengpa.naengpamasterbackend.member.entity.Member;
import com.naengpa.naengpamasterbackend.member.entity.MemberRole;
import com.naengpa.naengpamasterbackend.member.entity.MemberStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminMemberService {

    private final AdminMemberRepository adminMemberRepository;

    @Transactional(readOnly = true)
    public Page<AdminMemberResponse> getMembers(MemberRole role, MemberStatus status, String search, Pageable pageable) {
        return adminMemberRepository.findMembers(role, status, search, pageable)
                .map(AdminMemberResponse::from);
    }

    @Transactional
    public void updateMemberStatus(Long memberId, AdminMemberStatusRequest request) {
        Member member = adminMemberRepository.findById(memberId)
                .orElseThrow(MemberNotFoundException::new);
        member.updateStatus(request.status());
    }

    @Transactional
    public void updateMemberRole(Long memberId, AdminMemberRoleRequest request) {
        adminMemberRepository.findById(memberId)
                .orElseThrow(MemberNotFoundException::new)
                .updateRole(request.role());
    }
}
