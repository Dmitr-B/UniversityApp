package com.university.app.controller.restcontroller;

import com.university.app.dto.CreateLectureDto;
import com.university.app.dto.IdLocationDto;
import com.university.app.dto.LectureDto;
import com.university.app.service.LectureService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
public class LectureController {

    @Qualifier("lectureServiceImpl")
    private final LectureService lectureService;

    @PostMapping("/lecture")
    public ResponseEntity<IdLocationDto> create(@RequestBody @NotNull @Valid CreateLectureDto createLectureDto) {
        IdLocationDto idLocationDto = null;

        try {
            idLocationDto = lectureService.save(createLectureDto);
        } catch (URISyntaxException e) {
            log.error(e.getMessage());
        }

        if (idLocationDto == null || idLocationDto.getId() == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(idLocationDto);
    }

    @GetMapping("/lecture/{id}")
    public ResponseEntity<LectureDto> getById(@PathVariable @NotNull @Valid Long id) {

        LectureDto lectureDto = lectureService.getById(id);

        if (lectureDto == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(lectureDto);
    }

    @GetMapping("/lecture")
    public ResponseEntity<List<LectureDto>> getAll() {
        List<LectureDto> lectures = lectureService.getAll();

        if (lectures.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(lectures);
    }

    @PatchMapping("/lecture/{id}")
    public ResponseEntity<LectureDto> update(@PathVariable("id") @NotNull @Valid Long id,
                                             @RequestBody @NotNull @Valid CreateLectureDto createLectureDto) {

        LectureDto updatedLecture = lectureService.update(id, createLectureDto);

        if (updatedLecture == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedLecture);
    }

    @DeleteMapping("/lecture/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") @NotNull @Valid Long id) {
        lectureService.delete(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/student/{id}/lecture")
    public ResponseEntity<List<LectureDto>> getLectureForStudent(@PathVariable("id") Long studentId,
                                                                     @RequestParam("date")
                                                                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        List<LectureDto> studentLectures = lectureService.getLectureForStudent(studentId, date);

        if (studentLectures.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(studentLectures);
    }
}
