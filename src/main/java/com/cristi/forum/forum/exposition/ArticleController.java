package com.cristi.forum.forum.exposition;

import com.cristi.forum.forum.domain.article.Article;
import com.cristi.forum.forum.domain.article.Articles;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/article")
@Transactional
@Slf4j
public class ArticleController {
    @Autowired
    private Articles articles;
    @Autowired
    private ModelMapper modelMapper;

    @PutMapping
    public long createArticle(@RequestBody ArticleDto articleToCreate) {

        Article article = modelMapper.map(articleToCreate, Article.class);
        log.info("Saving article: {}", article);
        return articles.save(article).getId();
    }

    @GetMapping
    public List<ArticleDto> listArticles() {
        return articles.findAll().stream()
                .map(a -> modelMapper.map(a, ArticleDto.class))
                .collect(toList());
    }
}
