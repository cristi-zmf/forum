package com.cristi.forum.forum.domain.article;

import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@NoArgsConstructor(force = true)
public class ArticleSearchCriteria {
    private String articleName;
}
