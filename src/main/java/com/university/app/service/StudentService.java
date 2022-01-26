package com.university.app.service;

import com.university.app.dto.CreateStudentDto;
import com.university.app.dto.IdLocationDto;
import com.university.app.dto.StudentDto;

import java.net.URISyntaxException;
import java.util.List;

public interface StudentService {

    IdLocationDto save(CreateStudentDto createStudentDto) throws URISyntaxException;

    StudentDto getById(Long id);

    List<StudentDto> getAll();

    StudentDto update(Long id, CreateStudentDto updateStudent);

    void delete(Long id);
}
