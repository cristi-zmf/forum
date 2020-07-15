package com.cristi.forum.forum.domain.article;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Articles extends JpaRepository<Article, Long>, ArticlesSpecialized {

    Page<Article> findByIdGreaterThanOrderById(Long id, Pageable pageRequest);

    Page<Article> findByTitleContainingIgnoreCase(String titlePattern, Pageable pageRequest);
}
