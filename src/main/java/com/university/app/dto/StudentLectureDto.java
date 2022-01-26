package com.university.app.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class StudentLectureDto {

    private String lectureName;
    private LocalDate lectureDate;
    private LocalTime lectureTime;
    private TeacherDto teacher;
    private AudienceDto audience;

    public StudentLectureDto(String lectureName, LocalDate lectureDate, LocalTime lectureTime,
                             TeacherDto teacher, AudienceDto audience) {
        this.lectureName = lectureName;
        this.lectureDate = lectureDate;
        this.lectureTime = lectureTime;
        this.teacher = teacher;
        this.audience = audience;
    }
}