package com.university.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class CreateStudentLectureDto {

    @NotNull
    private Long studentId;

}
