package com.naengpa.naengpamasterbackend.admin.dto.response;


public record AdminDashboardResponse(Long adminCount,
                                     Long activeMembers,
                                     Long inactiveMembers,
                                     Long pendingInquiries) {}
