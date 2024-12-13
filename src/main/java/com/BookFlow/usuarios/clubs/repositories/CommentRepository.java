package com.BookFlow.usuarios.clubs.repositories;

import com.BookFlow.usuarios.clubs.domain.model.aggregates.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{
boolean existsByName(String content);
    Optional<Comment> findByName(String content);
}
