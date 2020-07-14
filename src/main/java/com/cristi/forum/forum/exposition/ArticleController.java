package com.cristi.forum.forum.exposition;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

@ApiRestController
@Transactional
@Slf4j
public class ArticleController {
    public static final String ARTICLES_API_PREFIX = "articles";
//    @Autowired
//    private Articles articles;
    @Autowired
    private ModelMapper modelMapper;


}
