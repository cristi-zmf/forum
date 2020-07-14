package com.cristi.forum.forum.infra;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
@Slf4j
//TODO
public class ArticleDataGeneratorRunner implements CommandLineRunner {
//    private final Articles articles;
//    @Value("${max.no.articles.to.generate}")
//    private long maxNoOrArticlesToGenerate;
//    private final Faker faker;
//
//
//    public ArticleDataGeneratorRunner(Articles articles) {
//        this.articles = articles;
//        faker = new Faker();
//    }

    @Override
    public void run(String... args) {
//        List<Article> generatedArticles = IntStream.iterate(1, s -> s = s + 1)
//                .mapToObj(n -> new Article(faker.chuckNorris().fact(), "http://" + faker.internet().url(), 0))
//                .limit(maxNoOrArticlesToGenerate)
//                .collect(toList());
//        log.info("Saving {} fake articles", maxNoOrArticlesToGenerate);
//        articles.saveAll(generatedArticles);
    }
}
