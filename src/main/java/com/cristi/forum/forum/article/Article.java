package com.cristi.forum.forum.article;

import com.cristi.forum.forum.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Entity;

@Entity
@Getter
@AllArgsConstructor
@ToString(callSuper = true)
public class Article extends BaseEntity {

    private String title;
    private String link;
    private int votes;
}
