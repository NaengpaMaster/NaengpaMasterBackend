package com.naengpa.naengpamasterbackend.comment.service;

import com.naengpa.naengpamasterbackend.comment.dto.response.CommentListResponse;
import com.naengpa.naengpamasterbackend.comment.entity.Comment;
import com.naengpa.naengpamasterbackend.comment.repository.CommentRepository;
import com.naengpa.naengpamasterbackend.global.exception.RecipeNotFoundException;
import com.naengpa.naengpamasterbackend.member.entity.Member;
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

    private void validateRecipeExists(Long recipeId) {
        recipeRepository.findByRecipeIdAndDeletedFalse(recipeId)
                .orElseThrow(RecipeNotFoundException::new);
    }
}
