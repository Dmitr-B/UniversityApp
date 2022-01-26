package com.university.app.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class CreateLectureDto {

    @NotNull
    @JsonProperty("lectureName")
    private String lectureName;

    @NotNull
    @JsonProperty("lectureDate")
    private LocalDate lectureDate;

    @NotNull
    @JsonProperty("lectureTime")
    private LocalTime lectureTime;

    @NotNull
    @JsonProperty("teacherId")
    private Long teacherId;

    @NotNull
    @JsonProperty("audienceId")
    private Long audienceId;

    @JsonCreator
    public CreateLectureDto(String lectureName, LocalDate lectureDate, LocalTime lectureTime, Long teacherId, Long audienceId) {
        this.lectureName = lectureName;
        this.lectureDate = lectureDate;
        this.lectureTime = lectureTime;
        this.teacherId = teacherId;
        this.audienceId = audienceId;
    }
}
