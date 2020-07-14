package com.cristi.forum.forum.domain.article;

import com.cristi.forum.forum.exposition.ArticleSearchCriteriaDto;

import java.util.List;

public interface ArticlesSpecialized {
    List<Article> findRandomArticles(int limit);
    List<Article> findNextArticlesWithIdGreaterThan(long id, int limit);

    List<Article> findByCriteria(ArticleSearchCriteriaDto searchCriteria, int limit);
}
