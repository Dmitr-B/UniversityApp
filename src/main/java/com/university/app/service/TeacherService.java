package com.university.app.service;

import com.university.app.dto.CreateTeacherDto;
import com.university.app.dto.IdLocationDto;
import com.university.app.dto.TeacherDto;

import java.net.URISyntaxException;
import java.util.List;

public interface TeacherService {

    IdLocationDto save(CreateTeacherDto createTeacherDto) throws URISyntaxException;

    TeacherDto getById(Long id);

    List<TeacherDto> getAll();

    TeacherDto update(Long id, CreateTeacherDto createTeacherDto);

    void delete(Long id);
}
