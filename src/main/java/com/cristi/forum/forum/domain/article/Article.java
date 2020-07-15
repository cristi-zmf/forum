package com.cristi.forum.forum.domain.article;

import com.cristi.forum.forum.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Value;

import javax.persistence.Entity;

@Value
@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class Article extends BaseEntity {
    private String title;
    private String link;
    private int votes;

}
