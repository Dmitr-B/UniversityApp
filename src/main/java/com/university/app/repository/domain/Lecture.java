package com.university.app.repository.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "lecture")
public class Lecture {

    @Id
    @GenericGenerator(name = "lecture_seq", strategy = "increment")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lecture_seq")
    private Long id;

    private String name;

    private LocalDate date;

    private LocalTime time;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "audience_id")
    private Audience audience;

    @Transient
    @ManyToMany(mappedBy = "lectures")
    private Set<Student> students = new HashSet<>();
}
