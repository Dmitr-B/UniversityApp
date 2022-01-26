package com.university.app.dto;

import lombok.Data;

@Data
public class AudienceDto {

    private Long id;
    private String audienceName;

    public AudienceDto(Long id, String audienceName) {
        this.id = id;
        this.audienceName = audienceName;
    }
}
