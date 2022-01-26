package com.university.app.controller.restcontroller;

import com.university.app.dto.CreateStudentDto;
import com.university.app.dto.IdLocationDto;
import com.university.app.dto.StudentDto;
import com.university.app.service.StudentService;
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
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/student")
    public ResponseEntity<IdLocationDto> create(@RequestBody @NotNull @Valid CreateStudentDto createStudentDto) {
        IdLocationDto idLocationDto = null;

        try {
            idLocationDto = studentService.save(createStudentDto);
        } catch (URISyntaxException e) {
            log.error(e.getMessage());
        }

        if (idLocationDto == null || idLocationDto.getId() == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(idLocationDto);
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<StudentDto> getById(@PathVariable @NotNull @Valid Long id) {

        StudentDto studentDto = studentService.getById(id);

        if (studentDto == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(studentDto);
    }

    @GetMapping("/student")
    public ResponseEntity<List<StudentDto>> getAll() {
        List<StudentDto> students = studentService.getAll();

        if (students.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(students);
    }

    @PatchMapping("/student/{id}")
    public ResponseEntity<StudentDto> update(@PathVariable("id") @NotNull @Valid Long id,
                                          @RequestBody @NotNull @Valid CreateStudentDto createStudentDto) {

        StudentDto updatedStudent = studentService.update(id, createStudentDto);

        if (updatedStudent == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") @NotNull @Valid Long id) {

        studentService.delete(id);

        return ResponseEntity.ok().build();
    }
}
