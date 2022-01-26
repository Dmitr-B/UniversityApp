package com.university.app.service.impl;

import com.university.app.dto.CreateStudentLectureDto;
import com.university.app.repository.StudentLectureRepository;
import com.university.app.repository.domain.StudentLecture;
import com.university.app.service.StudentLectureService;
import com.university.app.service.converter.StudentLectureConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Log4j2
public class StudentLectureServiceImpl implements StudentLectureService {

    private final StudentLectureConverter studentLectureConverter;
    private final StudentLectureRepository studentLectureRepository;

    @Override
    public void save(Long lectureId, CreateStudentLectureDto createStudentLectureDto) {
        StudentLecture studentLecture = studentLectureConverter.convert(createStudentLectureDto);
        studentLecture.setLectureId(lectureId);

        studentLectureRepository.save(studentLecture);
        //studentLectureRepository.saveStudentLecture(lectureId, studentLecture);
    }

    @Transactional
    @Override
    public void delete(Long lectureId, Long studentId) {
        studentLectureRepository.deleteStudentLecture(lectureId, studentId);
    }
}
