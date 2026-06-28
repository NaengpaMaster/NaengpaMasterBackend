package com.naengpa.naengpamasterbackend.comment.controller;

import com.naengpa.naengpamasterbackend.comment.dto.request.CommentCreateRequest;
import com.naengpa.naengpamasterbackend.comment.dto.response.CommentCreateResponse;
import com.naengpa.naengpamasterbackend.comment.dto.response.CommentListResponse;
import com.naengpa.naengpamasterbackend.comment.service.CommentService;
import com.naengpa.naengpamasterbackend.global.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/api/v1/recipes/{recipeId}/comments")
    public ResponseEntity<ApiResponse<CommentListResponse>> getComments(
            @PathVariable Long recipeId,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return ResponseEntity.ok(
                ApiResponse.success("댓글 목록 조회 성공", commentService.getComments(recipeId, pageable))
        );
    }

    @PostMapping("/api/v1/recipes/{recipeId}/comments")
    public ResponseEntity<ApiResponse<CommentCreateResponse>> createComment(
            @PathVariable Long recipeId,
            @Valid @RequestBody CommentCreateRequest request,
            Authentication authentication
    ) {
        CommentCreateResponse response =
                commentService.createComment(authentication.getName(), recipeId, request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("댓글이 등록되었습니다.", response));
    }
}
