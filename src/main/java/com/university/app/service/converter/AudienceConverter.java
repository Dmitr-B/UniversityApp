package com.university.app.service.converter;

import com.university.app.dto.AudienceDto;
import com.university.app.dto.CreateAudienceDto;
import com.university.app.repository.domain.Audience;
import org.springframework.stereotype.Component;

@Component
public class AudienceConverter {

    public AudienceDto convert(Audience audience) {
        return new AudienceDto(audience.getId(), audience.getName());
    }

    public Audience convert(CreateAudienceDto createAudienceDto) {
        Audience audience = new Audience();
        audience.setName(createAudienceDto.getAudienceName());

        return audience;
    }

    public Audience convert(AudienceDto audienceDto) {
        Audience audience = new Audience();
        audience.setId(audienceDto.getId());
        audience.setName(audienceDto.getAudienceName());

        return audience;
    }
}
