package com.cristi.forum.forum.exposition;

import com.cristi.forum.forum.domain.Article;
import com.cristi.forum.forum.domain.Articles;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

@ApiRestController
@Transactional
@Slf4j
public class ArticleController {
    public static final String ARTICLES_API_PREFIX = "articles";
    @Autowired
    private Articles articles;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(path = ARTICLES_API_PREFIX) // GET api/articles
    public List<ArticleDto> findAllArticles() {
        return articles.findAll().stream() // ????? cate or sa fie peste 1 luna, 1 an, 10 ani
                .map(mapToDto())
                .collect(Collectors.toList());
    }
    @GetMapping(path = ARTICLES_API_PREFIX + "/random/{nRandomArticles}") // GET api/articles
    public List<ArticleDto> findNRandomArticles(@PathVariable int nRandomArticles) {
        long total = articles.count();
        int randomPage = (int) (Math.random() * (total / nRandomArticles));
        return articles
                .findAll(PageRequest.of(randomPage, nRandomArticles, Sort.unsorted()))
                .getContent().stream()
                .map(mapToDto())
                .collect(Collectors.toList());
    }
    @GetMapping(path = ARTICLES_API_PREFIX + "/{id}/{pageSize}") // GET api/articles
    public List<ArticleDto> findNextArticles(@PathVariable long id, @PathVariable int pageSize) {
        System.out.println("Finding next articles for id: " + id);
        return articles
                .findByIdGreaterThanOrderById(id, PageRequest.of(0, pageSize))
                .stream()
                .map(mapToDto())
                .collect(Collectors.toList());
    }
    @PostMapping(path = ARTICLES_API_PREFIX) // POST api/articles
    public Long addArticle(@RequestBody ArticleDto dto) {
        Article newArticle = new Article(dto.title, dto.link, dto.votes);
        return articles.save(newArticle).getId();
    }

    private Function<Article, ArticleDto> mapToDto() {
        return a -> new ArticleDto(a.getId(), a.getTitle(), a.getLink(), a.getVotes());
    }

}
