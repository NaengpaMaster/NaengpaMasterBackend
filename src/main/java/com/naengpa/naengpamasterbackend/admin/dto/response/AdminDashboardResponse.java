package com.naengpa.naengpamasterbackend.admin.dto.response;

public record AdminDashboardResponse(Long adminCount,
                                     Long totalMembers,
                                     Long inactiveMembers,
                                     Long todayNewMembers,
                                     Long totalRecipes,
                                     Long pendingInquiries,
                                     Double averageScore,
                                     Long todayExpiredCount) {}
