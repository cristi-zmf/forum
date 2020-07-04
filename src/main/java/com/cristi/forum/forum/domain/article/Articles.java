package com.cristi.forum.forum.domain.article;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Articles extends JpaRepository<Article, Long>, ArticlesSpecialized {
}
