package com.cristi.forum.forum;

import lombok.*;

import javax.persistence.*;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Setter
@ToString
@MappedSuperclass
@Access(AccessType.FIELD)
public class BaseEntity {
    @Id
    @GeneratedValue
    protected Long id;
}
