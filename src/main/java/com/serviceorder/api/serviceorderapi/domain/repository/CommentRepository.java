package com.serviceorder.api.serviceorderapi.domain.repository;

import com.serviceorder.api.serviceorderapi.domain.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
