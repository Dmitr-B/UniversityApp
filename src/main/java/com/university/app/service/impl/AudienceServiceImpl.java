package com.university.app.service.impl;

import com.university.app.dto.AudienceDto;
import com.university.app.dto.CreateAudienceDto;
import com.university.app.dto.IdLocationDto;
import com.university.app.repository.AudienceRepository;
import com.university.app.repository.domain.Audience;
import com.university.app.service.AudienceService;
import com.university.app.service.converter.AudienceConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class AudienceServiceImpl implements AudienceService {

    private final AudienceRepository audienceRepository;
    private final AudienceConverter audienceConverter;

    @Override
    public IdLocationDto save(CreateAudienceDto createAudienceDto) throws URISyntaxException {
        Audience audience = audienceConverter.convert(createAudienceDto);

        audienceRepository.save(audience);

        return new IdLocationDto(audience.getId(), new URI("/audience/" + audience.getId()));
    }

    @Override
    public AudienceDto getById(Long id) {
        Audience audience = audienceRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Audience not found")
        );

        return audienceConverter.convert(audience);
    }

    @Override
    public List<AudienceDto> getAll() {
        List<Audience> audiences = audienceRepository.findAll();

        return audiences.stream().
                map(audienceConverter::convert).
                collect(Collectors.toList());
    }

    @Override
    public AudienceDto update(Long id, CreateAudienceDto updateAudience) {
        Audience audience = audienceRepository.getById(id);

        audience.setName(updateAudience.getAudienceName());

        audienceRepository.save(audience);

        return audienceConverter.convert(audience);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        audienceRepository.deleteById(id);
    }
}
