package com.cristi.forum.forum.domain;

import com.cristi.forum.forum.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Getter
public class Article extends BaseEntity {
    private String title;
    private String link;
    private int votes;

    public Article(Long id, String title, String link, int votes) {
        super(id);
        this.title = title;
        this.link = link;
        this.votes = votes;
    }
}
