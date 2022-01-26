package com.university.app.repository.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "audience")
public class Audience {

    @Id
    @GenericGenerator(name = "audience_seq", strategy = "increment")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "audience_seq")
    private Long id;

    private String name;

    @Transient
    @OneToMany(mappedBy="audience")
    private Set<Lecture> lectures;
}
