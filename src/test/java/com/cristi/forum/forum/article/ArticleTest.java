package com.cristi.forum.forum.article;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArticleTest {
    @Test
    void should_be_equals_by_id() {
        Article article = new Article("title", "cucu", 3);
        Article article2 = new Article("ciuciu", "blabla", 4);

        article.setId(1L);
        article2.setId(2L);

        Assertions.assertThat(article).isEqualTo(article2);
    }
}