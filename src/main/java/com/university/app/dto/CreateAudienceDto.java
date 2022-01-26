package com.university.app.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CreateAudienceDto {

    @NotNull
    private String audienceName;

    @JsonCreator
    public CreateAudienceDto(String audienceName) {
        this.audienceName = audienceName;
    }
}
