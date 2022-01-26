package com.university.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.net.URI;

@Data
@AllArgsConstructor
public class IdLocationDto {

    private Long id;
    private URI location;
}
