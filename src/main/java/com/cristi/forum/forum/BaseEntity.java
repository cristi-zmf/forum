package com.cristi.forum.forum;

import lombok.*;

import javax.persistence.Id;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Setter
@ToString
public class BaseEntity {
    @Id
    private long id;
}
