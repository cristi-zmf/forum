package com.cristi.forum.forum.domain.article;

import com.cristi.forum.forum.exposition.ArticleSearchCriteriaDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
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
        log.debug("Found random articles {}", randomArticles);
        return randomArticles;
    }

    @Override
    public List<Article> findNextArticlesWithIdGreaterThan(long id, int limit) {
        return articles.findByIdGreaterThan(id, PageRequest.of(0, limit))
                .getContent();
    }

    @Override
    public List<Article> findByCriteria(ArticleSearchCriteriaDto searchCriteria, int limit) {
        Page<Article> pageResults = articles.findByTitleContainingIgnoreCase(
                searchCriteria.getArticleName(), PageRequest.of(0, limit)
        );
        return pageResults.getContent();
    }
}
