package com.university.app.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CreateTeacherDto {

    @NotNull
    @JsonProperty("firstName")
    private String firstName;
    @NotNull
    @JsonProperty("lastName")
    private String lastName;

    @JsonCreator
    public CreateTeacherDto(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
