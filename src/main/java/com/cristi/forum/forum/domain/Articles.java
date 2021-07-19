package com.cristi.forum.forum.domain;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Articles extends JpaRepository<Article, Long> {
    List<Article> findByIdGreaterThanOrderById(Long id, Pageable pageRequest);
}
