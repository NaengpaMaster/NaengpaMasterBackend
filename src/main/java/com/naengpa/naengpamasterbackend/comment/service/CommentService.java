package com.naengpa.naengpamasterbackend.comment.service;

import com.naengpa.naengpamasterbackend.comment.dto.request.CommentCreateRequest;
import com.naengpa.naengpamasterbackend.comment.dto.request.CommentUpdateRequest;
import com.naengpa.naengpamasterbackend.comment.dto.response.CommentCreateResponse;
import com.naengpa.naengpamasterbackend.comment.dto.response.CommentListResponse;
import com.naengpa.naengpamasterbackend.comment.entity.Comment;
import com.naengpa.naengpamasterbackend.comment.repository.CommentRepository;
import com.naengpa.naengpamasterbackend.global.exception.CommentAccessDeniedException;
import com.naengpa.naengpamasterbackend.global.exception.CommentNotFoundException;
import com.naengpa.naengpamasterbackend.global.exception.MemberNotFoundException;
import com.naengpa.naengpamasterbackend.global.exception.RecipeNotFoundException;
import com.naengpa.naengpamasterbackend.member.entity.Member;
import com.naengpa.naengpamasterbackend.member.entity.MemberRole;
import com.naengpa.naengpamasterbackend.member.repository.MemberRepository;
import com.naengpa.naengpamasterbackend.recipe.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;
    private final RecipeRepository recipeRepository;
    private final MemberRepository memberRepository;

    public CommentListResponse getComments(Long recipeId, Pageable pageable) {
        validateRecipeExists(recipeId);

        Page<Comment> comments = commentRepository.findByRecipeIdAndDeletedFalseOrderByCreatedAtAsc(recipeId, pageable);

        List<Long> memberIds = comments.getContent().stream()
                .map(Comment::getMemberId)
                .distinct()
                .toList();

        Map<Long, String> writerNicknames = memberIds.isEmpty()
                ? Map.of()
                : memberRepository.findAllById(memberIds).stream()
                        .collect(Collectors.toMap(Member::getId, Member::getNickname));

        return CommentListResponse.from(comments, writerNicknames);
    }

    @Transactional
    public CommentCreateResponse createComment(String email, Long recipeId, CommentCreateRequest request) {
        validateRecipeExists(recipeId);

        Member member = memberRepository.findByEmail(email)
                .orElseThrow(MemberNotFoundException::new);

        Comment comment = commentRepository.save(
                Comment.create(recipeId, member.getId(), request.content())
        );

        return new CommentCreateResponse(comment.getCommentId());
    }

    @Transactional
    public void updateComment(String email, Long commentId, CommentUpdateRequest request) {
        Comment comment = commentRepository.findByCommentIdAndDeletedFalse(commentId)
                .orElseThrow(CommentNotFoundException::new);

        Member member = memberRepository.findByEmail(email)
                .orElseThrow(MemberNotFoundException::new);

        if (!comment.isWrittenBy(member.getId())) {
            throw new CommentAccessDeniedException("댓글 수정 권한이 없습니다.");
        }

        comment.updateContent(request.content());
    }

    @Transactional
    public void deleteComment(String email, Long commentId) {
        Comment comment = commentRepository.findByCommentIdAndDeletedFalse(commentId)
                .orElseThrow(CommentNotFoundException::new);

        Member member = memberRepository.findByEmail(email)
                .orElseThrow(MemberNotFoundException::new);

        boolean isAdmin = member.getRole() == MemberRole.ADMIN;
        if (!comment.isWrittenBy(member.getId()) && !isAdmin) {
            throw new CommentAccessDeniedException("댓글 삭제 권한이 없습니다.");
        }

        comment.softDelete();
    }

    private void validateRecipeExists(Long recipeId) {
        recipeRepository.findByRecipeIdAndDeletedFalse(recipeId)
                .orElseThrow(RecipeNotFoundException::new);
    }
}
