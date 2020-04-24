package com.gabriel.springalgawjava.domain.repositories;

import com.gabriel.springalgawjava.domain.models.Comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
}