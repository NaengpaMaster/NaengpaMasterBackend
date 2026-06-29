package com.naengpa.naengpamasterbackend.comment.dto.response;

import com.naengpa.naengpamasterbackend.comment.entity.Comment;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public record CommentListResponse(
        List<CommentItem> comments,
        int page,
        int size,
        long totalElements,
        int totalPages
) {

    public static CommentListResponse from(Page<Comment> page, Map<Long, String> writerNicknames) {
        List<CommentItem> items = page.getContent().stream()
                .map(comment -> CommentItem.of(comment, writerNicknames.get(comment.getMemberId())))
                .toList();
        return new CommentListResponse(
                items,
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages()
        );
    }

    public record CommentItem(
            Long commentId,
            String writerNickname,
            String content,
            LocalDateTime createdAt,
            boolean modified
    ) {
        public static CommentItem of(Comment comment, String writerNickname) {
            return new CommentItem(
                    comment.getCommentId(),
                    writerNickname,
                    comment.getContent(),
                    comment.getCreatedAt(),
                    comment.isModified()
            );
        }
    }
}
