package com.cristi.forum.forum.exposition;

import com.cristi.forum.forum.domain.article.Article;
import lombok.Data;

@Data
public class ArticleDto {
    public Long id;
    public String title;
    public String link;
    public int votes;
}
