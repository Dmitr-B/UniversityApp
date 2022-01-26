package com.university.app.repository.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "teacher")
public class Teacher {

    @Id
    @GenericGenerator(name = "teacher_seq", strategy = "increment")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teacher_seq")
    private Long id;

    private String firstName;

    private String lastName;

    @Transient
    @OneToMany(mappedBy="teacher")
    private Set<Lecture> lectures;
}
