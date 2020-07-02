package com.cristi.forum.forum.exposition;

import lombok.Data;

@Data
public class ArticleDto {
    private Long id;
    private String title;
    private String link;
    private int votes;
}
