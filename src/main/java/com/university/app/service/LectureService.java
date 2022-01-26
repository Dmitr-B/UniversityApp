package com.university.app.service;

import com.university.app.dto.CreateLectureDto;
import com.university.app.dto.IdLocationDto;
import com.university.app.dto.LectureDto;

import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;

public interface LectureService {

    IdLocationDto save(CreateLectureDto createLectureDto) throws URISyntaxException;

    LectureDto getById(Long id);

    List<LectureDto> getAll();

    List<LectureDto> getLectureForStudent(Long studentId, LocalDate lectureDate);

    LectureDto update(Long id, CreateLectureDto createLectureDto);

    void delete(Long id);
}
