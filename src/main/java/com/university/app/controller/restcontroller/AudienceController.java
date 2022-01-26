package com.university.app.controller.restcontroller;

import com.university.app.dto.AudienceDto;
import com.university.app.dto.CreateAudienceDto;
import com.university.app.dto.IdLocationDto;
import com.university.app.service.AudienceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
public class AudienceController {

    private final AudienceService audienceService;

    @PostMapping("/audience")
    public ResponseEntity<IdLocationDto> create(@RequestBody @NotNull @Valid CreateAudienceDto createAudienceDto) {
        IdLocationDto idLocationDto = null;

        try {
            idLocationDto = audienceService.save(createAudienceDto);
        } catch (URISyntaxException e) {
            log.error(e.getMessage());
        }

        if (idLocationDto == null || idLocationDto.getId() == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(idLocationDto);
    }

    @GetMapping("/audience/{id}")
    public ResponseEntity<AudienceDto> getById(@PathVariable @NotNull @Valid Long id) {

        AudienceDto audienceDto = audienceService.getById(id);

        if (audienceDto == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(audienceDto);
    }

    @GetMapping("/audience")
    public ResponseEntity<List<AudienceDto>> getAll() {
        List<AudienceDto> audiences = audienceService.getAll();

        if (audiences.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(audiences);
    }

    @PatchMapping("/audience/{id}")
    public ResponseEntity<AudienceDto> update(@PathVariable("id") @NotNull @Valid Long id,
                                             @RequestBody @NotNull @Valid CreateAudienceDto createAudienceDto) {

        AudienceDto updatedAudience = audienceService.update(id, createAudienceDto);

        if (updatedAudience == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedAudience);
    }

    @DeleteMapping("/audience/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        audienceService.delete(id);

        return ResponseEntity.ok().build();
    }
}
