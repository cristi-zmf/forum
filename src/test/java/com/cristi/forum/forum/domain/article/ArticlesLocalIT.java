package com.cristi.forum.forum.domain.article;

import com.cristi.forum.forum.BaseLocalIT;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class ArticlesLocalIT extends BaseLocalIT {

    @Autowired
    private Articles articles;

    @Test
    void should_persist_entity() {
        Article someArticle = new Article("Cool article", "www.cool.com", 3);
        articles.save(someArticle);

        Assertions.assertThat(someArticle.getId()).isNotNull();
        Article savedOne = articles.getOne(someArticle.getId());

        Assertions.assertThat(savedOne).isEqualToComparingFieldByField(someArticle);
    }
}