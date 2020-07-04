package com.cristi.forum.forum.domain.article;

import java.util.List;

public interface ArticlesSpecialized {
    List<Article> findRandomArticles(int limit);
    List<Article> findNextArticlesWithIdGreaterThan(long id, int limit);
}
