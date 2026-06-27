package com.naengpa.naengpamasterbackend.admin.controller;

import com.naengpa.naengpamasterbackend.admin.service.AdminInquiryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/inquiries")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminInquiriesController {

    private final AdminInquiryService adminInquiryService;
}
