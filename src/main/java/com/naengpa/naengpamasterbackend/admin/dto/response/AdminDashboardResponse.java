package com.naengpa.naengpamasterbackend.admin.dto.response;

public record AdminDashboardResponse(Long adminCount,
                                     Long totalMembers,
                                     Long activeMembers,
                                     Long inactiveMembers,
                                     Long pendingInquiries) {}
