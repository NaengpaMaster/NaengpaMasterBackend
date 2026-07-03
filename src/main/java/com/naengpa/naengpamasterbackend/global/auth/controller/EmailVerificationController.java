package com.naengpa.naengpamasterbackend.global.auth.controller;

import com.naengpa.naengpamasterbackend.global.auth.dto.EmailVerificationConfirmRequest;
import com.naengpa.naengpamasterbackend.global.auth.dto.EmailVerificationRequest;
import com.naengpa.naengpamasterbackend.global.auth.service.EmailVerificationService;
import com.naengpa.naengpamasterbackend.global.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth/email-verifications")
public class EmailVerificationController {

    private final EmailVerificationService emailVerificationService;

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> sendVerificationCode(
            @Valid @RequestBody EmailVerificationRequest request
    ) {
        emailVerificationService.sendVerificationCode(request.email());
        return ResponseEntity.ok(ApiResponse.success("인증 코드가 발송되었습니다.", null));
    }

    @PostMapping("/confirm")
    public ResponseEntity<ApiResponse<Void>> confirmVerificationCode(
            @Valid @RequestBody EmailVerificationConfirmRequest request
    ) {
        emailVerificationService.confirmVerificationCode(request.email(), request.code());
        return ResponseEntity.ok(ApiResponse.success("이메일 인증이 완료되었습니다.", null));
    }
}
