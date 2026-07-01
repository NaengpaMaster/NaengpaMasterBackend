package com.naengpa.naengpamasterbackend.comment.repository;

import com.naengpa.naengpamasterbackend.comment.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Page<Comment> findByRecipeIdAndDeletedFalseOrderByCreatedAtAsc(Long recipeId, Pageable pageable);

    Optional<Comment> findByCommentIdAndDeletedFalse(Long commentId);
}
