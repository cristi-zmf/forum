package com.cristi.forum.forum.domain.article;

import java.util.List;

public interface ArticlesSpecialized {
    List<Article> findRandomArticles(int maxNumberOfArticles);

    List<Article> findNextArticles(Long id, int limit);

    List<Article> searchFirstNArticles(ArticleSearchCriteria criteria, int limit);
}
