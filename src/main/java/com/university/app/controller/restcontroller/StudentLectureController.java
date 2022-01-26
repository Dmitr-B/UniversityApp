package com.university.app.controller.restcontroller;

import com.university.app.dto.CreateStudentLectureDto;
import com.university.app.service.StudentLectureService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/university")
public class StudentLectureController {

    private final StudentLectureService studentLectureService;

    @PostMapping("/lecture/{id}/student")
    public ResponseEntity<Void> saveStudentLecture(@PathVariable("id") @NotNull @Valid Long id,
                                                   @RequestBody @NotNull @Valid CreateStudentLectureDto createStudentLectureDto) {

        studentLectureService.save(id, createStudentLectureDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/lecture/{lectureId}/student/{studentId}")
    public ResponseEntity<Void> deleteStudentLecture(@PathVariable("lectureId") @NotNull @Valid Long lectureId,
                                                     @PathVariable("studentId") @NotNull @Valid Long studentId) {

        studentLectureService.delete(lectureId, studentId);

        return ResponseEntity.ok().build();
    }
}
