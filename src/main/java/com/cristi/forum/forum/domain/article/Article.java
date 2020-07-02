package com.cristi.forum.forum.domain.article;

import com.cristi.forum.forum.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@AllArgsConstructor
@ToString(callSuper = true)
@NoArgsConstructor
public class Article extends BaseEntity {
    @Column
    private String title;
    @Column
    private String link;
    @Column
    private int votes;
}
