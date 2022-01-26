package com.university.app.service;

import com.university.app.dto.CreateStudentLectureDto;

public interface StudentLectureService {

    void save(Long lectureId, CreateStudentLectureDto createStudentLectureDto);

    void delete(Long lectureId, Long studentId);
}
