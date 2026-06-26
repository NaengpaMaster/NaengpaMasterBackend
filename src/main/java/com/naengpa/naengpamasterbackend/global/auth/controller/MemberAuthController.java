package com.naengpa.naengpamasterbackend.global.auth.controller;

import com.naengpa.naengpamasterbackend.global.auth.dto.EmailAvailabilityResponse;
import com.naengpa.naengpamasterbackend.global.auth.dto.MemberResponse;
import com.naengpa.naengpamasterbackend.global.auth.dto.SignUpRequest;
import com.naengpa.naengpamasterbackend.global.auth.service.AuthService;
import com.naengpa.naengpamasterbackend.global.exception.DuplicateEmailException;
import com.naengpa.naengpamasterbackend.global.response.ApiResponse;
import jakarta.validation.constraints.Email;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.annotation.Validated;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
@Validated
public class MemberAuthController {

    private final AuthService authService;

    @PostMapping
    public ResponseEntity<ApiResponse<MemberResponse>> signup(@Valid @RequestBody SignUpRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("회원가입이 완료되었습니다.", authService.signup(request)));
    }

    @GetMapping("/check-email")
    public ResponseEntity<ApiResponse<EmailAvailabilityResponse>> checkEmail(@RequestParam @Email String email) {
        boolean available = authService.isEmailAvailable(email);
        if (!available) {
            throw new DuplicateEmailException();
        }
        return ResponseEntity.ok(ApiResponse.success("사용 가능한 이메일입니다.", new EmailAvailabilityResponse(true)));
    }

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<MemberResponse>> me(Authentication authentication) {
        return ResponseEntity.ok(ApiResponse.success(authService.getMember(authentication.getName())));
    }
}
