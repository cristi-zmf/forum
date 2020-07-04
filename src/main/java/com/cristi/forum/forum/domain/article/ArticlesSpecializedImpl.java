package com.cristi.forum.forum.domain.article;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class ArticlesSpecializedImpl implements ArticlesSpecialized {
    private final Articles articles;

    public ArticlesSpecializedImpl(Articles articles) {
        this.articles = articles;
    }

    @Override
    public List<Article> findRandomArticles(int limit) {
        int randomPage = (int) (articles.count() / limit * Math.random());
        log.info("Random page selected: {}", randomPage);
        List<Article> randomArticles = articles.findAll(PageRequest.of(randomPage, limit)).getContent();
        log.info("Found random articles {}", randomArticles);
        return randomArticles;
    }
}
