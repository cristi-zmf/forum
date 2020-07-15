package com.cristi.forum.forum.domain.article;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ArticlesSpecializedImpl implements ArticlesSpecialized {

    private Articles articles;

    public ArticlesSpecializedImpl(Articles articles) {
        this.articles = articles;
    }

    @Override
    public List<Article> findRandomArticles(int maxNumberOfArticles) {
        long qty = articles.count(); //qty / sizeOfPage = numar pagini
        int randomPageIndex = (int) (Math.random() * (qty / maxNumberOfArticles));
        Page<Article> randomArticlePage = articles.findAll(PageRequest.of(randomPageIndex, maxNumberOfArticles));
        return randomArticlePage.getContent();
    }

    @Override
    public List<Article> findNextArticles(Long id, int limit) {
        Page<Article> paginatedResults = articles.findByIdGreaterThanOrderById(id, PageRequest.of(0, limit));
        return paginatedResults.getContent();
    }

    @Override
    public List<Article> searchFirstNArticles(ArticleSearchCriteria criteria, int limit) {
        Page<Article> paginatedResults = articles.findByTitleContainingIgnoreCase(
                criteria.getArticleName(), PageRequest.of(0, limit)
        );
        return paginatedResults.getContent();
    }
}
