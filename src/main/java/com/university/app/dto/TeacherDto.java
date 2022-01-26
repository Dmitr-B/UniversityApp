package com.university.app.dto;

import lombok.Data;

@Data
public class TeacherDto {

    private Long id;
    private String firstName;
    private String lastName;

    public TeacherDto(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
