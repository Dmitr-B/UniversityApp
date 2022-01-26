package com.university.app.repository.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Entity
@Table(name = "student_lecture")
@IdClass(StudentLectureId.class)
public class StudentLecture {

    @Id
    private Long studentId;
    @Id
    private Long lectureId;
}
