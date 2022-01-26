package com.university.app.controller.restcontroller;

import com.university.app.dto.CreateTeacherDto;
import com.university.app.dto.IdLocationDto;
import com.university.app.dto.TeacherDto;
import com.university.app.service.TeacherService;
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
public class TeacherController {

    private final TeacherService teacherService;

    @PostMapping("/teacher")
    public ResponseEntity<IdLocationDto> create(@RequestBody @NotNull @Valid CreateTeacherDto createTeacherDto) {
        IdLocationDto idLocationDto = null;

        try {
            idLocationDto = teacherService.save(createTeacherDto);
        } catch (URISyntaxException e) {
            log.error(e.getMessage());
        }

        if (idLocationDto == null || idLocationDto.getId() == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(idLocationDto);
    }

    @GetMapping("/teacher/{id}")
    public ResponseEntity<TeacherDto> getById(@PathVariable @NotNull @Valid Long id) {

        TeacherDto teacherDto = teacherService.getById(id);

        if (teacherDto == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(teacherDto);
    }

    @GetMapping("/teacher")
    public ResponseEntity<List<TeacherDto>> getAll() {
        List<TeacherDto> teachers = teacherService.getAll();

        if (teachers.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(teachers);
    }

    @PatchMapping("/teacher/{id}")
    public ResponseEntity<TeacherDto> update(@PathVariable("id") @NotNull @Valid Long id,
                                             @RequestBody @NotNull @Valid CreateTeacherDto createTeacherDto) {

        TeacherDto updatedTeacher = teacherService.update(id, createTeacherDto);

        if (updatedTeacher == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedTeacher);
    }

    @DeleteMapping("/teacher/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        teacherService.delete(id);

        return ResponseEntity.ok().build();
    }
}
