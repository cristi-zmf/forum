package com.cristi.forum.forum.exposition;

import com.cristi.forum.forum.domain.article.Article;
import com.cristi.forum.forum.domain.article.ArticleSearchCriteria;
import com.cristi.forum.forum.domain.article.Articles;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@ApiRestController
@Transactional
@Slf4j
public class ArticleController {
    public static final String ARTICLES_API_PREFIX = "articles";
//    @Autowired
    private Articles articles;

//    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public ArticleController(Articles articles, ModelMapper modelMapper) {
        this.articles = articles;
        this.modelMapper = modelMapper;
    }


    @GetMapping(path = ARTICLES_API_PREFIX) //localhost:4200/api/articles
    public List<ArticleDto> findAllArticles() {
        return articles.findAll().stream()
                .map(article -> modelMapper.map(article, ArticleDto.class))
                .collect(toList());
    }


    @PostMapping(path = ARTICLES_API_PREFIX) //localhost:4200/api/articles  //Post -> modificam resursa existenta, Put -> creeam o noua resursa
    public Long modifyExistingArticle(@RequestBody ArticleDto articleDto) {
        Article articleToModify = modelMapper.map(articleDto, Article.class);
        return articles.saveAndFlush(articleToModify).getId();
    }
    @GetMapping(path = ARTICLES_API_PREFIX + "/random/{maxNumberOfArticles}") //localhost:4200/api/articles
    public List<ArticleDto> findNRandomArticles(@PathVariable int maxNumberOfArticles) {
        return articles.findRandomArticles(maxNumberOfArticles).stream()
                .map(article -> modelMapper.map(article, ArticleDto.class))
                .collect(toList());
    }
    @GetMapping(path = ARTICLES_API_PREFIX + "/{id}/{limit}") //localhost:4200/api/articles
    public List<ArticleDto> searchArticles(@PathVariable Long id, @PathVariable int limit) {
        return articles.findNextArticles(id, limit).stream()
                .map(article -> modelMapper.map(article, ArticleDto.class))
                .collect(toList());
    }
    @PostMapping(path = ARTICLES_API_PREFIX + "/search") //localhost:4200/api/articles
    public List<ArticleDto> searchArticles(@RequestBody ArticleSearchCriteriaDto searchCriteriaDto) {
        log.info("Criteria to search received from UI for {}", searchCriteriaDto);

        ArticleSearchCriteria criteria = modelMapper.map(searchCriteriaDto, ArticleSearchCriteria.class);
        log.info("Criteria to search for {}", criteria);
        return articles.searchFirstNArticles(criteria, 10).stream()
                .map(article -> modelMapper.map(article, ArticleDto.class))
                .collect(toList());
    }

}
