package com.university.app.dto;

import com.university.app.repository.domain.Audience;
import com.university.app.repository.domain.Teacher;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class LectureDto {

    private Long id;
    private String lectureName;
    private LocalDate lectureDate;
    private LocalTime lectureTime;
    private TeacherDto teacher;
    private AudienceDto audience;

    public LectureDto(Long id, String lectureName, LocalDate lectureDate, LocalTime lectureTime,
                      TeacherDto teacher, AudienceDto audience) {
        this.id = id;
        this.lectureName = lectureName;
        this.lectureDate = lectureDate;
        this.lectureTime = lectureTime;
        this.teacher = teacher;
        this.audience = audience;
    }
}
