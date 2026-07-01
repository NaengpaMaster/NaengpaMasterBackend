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

    public static CommentListResponse from(Page<Comment> page, Map<Long, String> writerNicknames,
                                           Long currentMemberId, boolean isAdmin) {
        List<CommentItem> items = page.getContent().stream()
                .map(comment -> CommentItem.of(comment, writerNicknames.get(comment.getMemberId()),
                        currentMemberId, isAdmin))
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
            boolean modified,
            boolean canEdit,
            boolean canDelete
    ) {
        public static CommentItem of(Comment comment, String writerNickname, Long currentMemberId, boolean isAdmin) {
            boolean isWriter = currentMemberId != null && comment.isWrittenBy(currentMemberId);
            return new CommentItem(
                    comment.getCommentId(),
                    writerNickname,
                    comment.getContent(),
                    comment.getCreatedAt(),
                    comment.isModified(),
                    isWriter,
                    isWriter || isAdmin
            );
        }
    }
}
