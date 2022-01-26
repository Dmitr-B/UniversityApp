package com.university.app.service;

import com.university.app.dto.AudienceDto;
import com.university.app.dto.CreateAudienceDto;
import com.university.app.dto.IdLocationDto;

import java.net.URISyntaxException;
import java.util.List;

public interface AudienceService {

    IdLocationDto save(CreateAudienceDto createAudienceDto) throws URISyntaxException;

    AudienceDto getById(Long id);

    List<AudienceDto> getAll();

    AudienceDto update(Long id, CreateAudienceDto updateAudience);

    void delete(Long id);
}
