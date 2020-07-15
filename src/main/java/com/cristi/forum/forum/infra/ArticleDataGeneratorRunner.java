package com.cristi.forum.forum.infra;

import com.cristi.forum.forum.domain.article.Article;
import com.cristi.forum.forum.domain.article.Articles;
import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

@Component
@Transactional
@Slf4j
public class ArticleDataGeneratorRunner implements CommandLineRunner {
    private final Articles articles;
    @Value("${max.no.articles.to.generate}")
    private long maxNoOrArticlesToGenerate;
    private final Faker faker;


    public ArticleDataGeneratorRunner(Articles articles) {
        this.articles = articles;
        faker = new Faker();
    }

    @Override
    public void run(String... args) {
        List<Article> generatedArticles = IntStream.iterate(1, s -> s = s + 1)
                .mapToObj(i -> new Article(faker.chuckNorris().fact(), "http://" + faker.internet().url(), 0))
                .limit(maxNoOrArticlesToGenerate)
                .collect(toList());
        log.info("Saving {} fake articles", maxNoOrArticlesToGenerate);
        articles.saveAll(generatedArticles);
    }
}
