package com.university.app.service;

import com.university.app.dto.AudienceDto;
import com.university.app.dto.CreateAudienceDto;
import com.university.app.dto.IdLocationDto;
import com.university.app.repository.AudienceRepository;
import com.university.app.repository.domain.Audience;
import com.university.app.service.converter.AudienceConverter;
import com.university.app.service.impl.AudienceServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AudienceServiceUnitTest {

    private static final long AUDIENCE_ID = 1;
    private static final String AUDIENCE_NAME = "testAudience";

    @InjectMocks
    private AudienceServiceImpl audienceService;
    @Mock
    private AudienceRepository audienceRepository;
    @Mock
    private AudienceConverter audienceConverter;

    @Test
    void saveAudience_whenSave_theOk() throws URISyntaxException {
        IdLocationDto expected = new IdLocationDto(AUDIENCE_ID, new URI("/audience/" + AUDIENCE_ID));
        Audience testAudience = new Audience();
        testAudience.setId(AUDIENCE_ID);
        testAudience.setName(AUDIENCE_NAME);

        when(audienceConverter.convert((CreateAudienceDto) any())).thenReturn(testAudience);

        IdLocationDto actual = audienceService.save(new CreateAudienceDto(AUDIENCE_NAME));

        verify(audienceRepository).save(testAudience);

        assertEquals(expected, actual);
    }

    @Test
    void getAudienceById_whenExist_theOk() {
        Audience testAudienceInDB = new Audience();
        testAudienceInDB.setId(AUDIENCE_ID);
        testAudienceInDB.setName(AUDIENCE_NAME);

        AudienceDto expected = new AudienceDto(AUDIENCE_ID, AUDIENCE_NAME);

        when(audienceRepository.findById(AUDIENCE_ID)).thenReturn(java.util.Optional.of(testAudienceInDB));
        when(audienceConverter.convert(testAudienceInDB)).thenReturn(expected);

        AudienceDto actual = audienceService.getById(AUDIENCE_ID);

        assertEquals(expected, actual);
    }

    @Test
    void getAudienceById_whenDoesNotExist_thenThrow() {
        String expected = "Audience not found";

        when(audienceRepository.findById(AUDIENCE_ID)).thenReturn(Optional.empty());

        IllegalArgumentException actual = assertThrows(IllegalArgumentException.class,
                () -> audienceService.getById(AUDIENCE_ID));

        assertEquals(expected, actual.getMessage());
    }

    @Test
    void getAllAudiences_whenExist_theOk() {
        List<Audience> audiencesInDB = new ArrayList<>();
        Audience audience = new Audience();
        audience.setId(AUDIENCE_ID);
        audience.setName(AUDIENCE_NAME);
        audiencesInDB.add(audience);

        List<AudienceDto> expected = new ArrayList<>();
        AudienceDto audienceDto = new AudienceDto(AUDIENCE_ID, AUDIENCE_NAME);
        expected.add(audienceDto);

        when(audienceRepository.findAll()).thenReturn(audiencesInDB);
        when(audienceConverter.convert(audience)).thenReturn(audienceDto);

        List<AudienceDto> actual = audienceService.getAll();

        assertEquals(expected, actual);
    }

    @Test
    void updateAudience_whenHasAudienceId_thenUpdate() {
        Audience testAudienceInDB = new Audience();
        testAudienceInDB.setId(AUDIENCE_ID);
        testAudienceInDB.setName(AUDIENCE_NAME);

        AudienceDto expected = new AudienceDto(AUDIENCE_ID, "updatedAudienceName");

        when(audienceRepository.getById(AUDIENCE_ID)).thenReturn(testAudienceInDB);
        when(audienceConverter.convert(testAudienceInDB)).thenReturn(expected);

        AudienceDto actual = audienceService.update(AUDIENCE_ID, new CreateAudienceDto("updatedAudienceName"));

        assertEquals(expected, actual);
    }

    @Test
    void deleteAudience_whenHasAudienceId_thenDelete() {
        audienceService.delete(AUDIENCE_ID);

        verify(audienceRepository).deleteById(AUDIENCE_ID);
    }
}
