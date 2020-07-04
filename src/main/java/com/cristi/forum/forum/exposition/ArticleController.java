package com.cristi.forum.forum.exposition;

import com.cristi.forum.forum.domain.article.Article;
import com.cristi.forum.forum.domain.article.Articles;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

import static java.util.stream.Collectors.toList;

@ApiRestController
@Transactional
@Slf4j
public class ArticleController {
    public static final String ARTICLES_API_PREFIX = "articles";
    @Autowired
    private Articles articles;
    @Autowired
    private ModelMapper modelMapper;

    @PutMapping(ARTICLES_API_PREFIX)
    public long createArticle(@RequestBody ArticleDto articleToCreate) {
        log.info("Article information received from request: {}", articleToCreate);
        Article article = modelMapper.map(articleToCreate, Article.class);
        log.info("Saving article: {}", article);
        return articles.save(article).getId();
    }

    @GetMapping(ARTICLES_API_PREFIX)
    public List<ArticleDto> listArticles() {
        log.info("Number of articles to be retrieved {}", articles.count());
        return articles.findAll().stream()
                .map(a -> modelMapper.map(a, ArticleDto.class))
                .collect(toList());

    }

    @GetMapping(ARTICLES_API_PREFIX + "/random/{size}")
    public List<ArticleDto> getRandomNArticles(@PathVariable int size) {
        List<Article> randomArticles = articles.findRandomArticles(size);
        log.info("Number of articles to be retrieved {}", randomArticles.size());
        return randomArticles.stream()
                .map(a -> modelMapper.map(a, ArticleDto.class))
                .collect(toList());
    }

    @GetMapping(ARTICLES_API_PREFIX + "/{id}/{size}")
    public List<ArticleDto> getNextNArticles(@PathVariable long id, @PathVariable int size) {
        List<Article> nextArticles = articles.findNextArticlesWithIdGreaterThan(id, size);
        log.info("Number of articles to be retrieved {}", nextArticles.size());
        return nextArticles.stream()
                .map(a -> modelMapper.map(a, ArticleDto.class))
                .collect(toList());
    }
}
