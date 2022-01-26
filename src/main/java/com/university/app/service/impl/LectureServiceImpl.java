package com.university.app.service.impl;

import com.university.app.dto.CreateLectureDto;
import com.university.app.dto.IdLocationDto;
import com.university.app.dto.LectureDto;
import com.university.app.repository.AudienceRepository;
import com.university.app.repository.LectureRepository;
import com.university.app.repository.TeacherRepository;
import com.university.app.repository.domain.Lecture;
import com.university.app.service.LectureService;
import com.university.app.service.converter.LectureConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class LectureServiceImpl implements LectureService {

    private final LectureRepository lectureRepository;
    private final TeacherRepository teacherRepository;
    private final AudienceRepository audienceRepository;
    private final LectureConverter lectureConverter;

    @Override
    public IdLocationDto save(CreateLectureDto createLectureDto) throws URISyntaxException {
        Lecture lecture = lectureConverter.convert(createLectureDto);

        lectureRepository.save(lecture);

        return new IdLocationDto(lecture.getId(), new URI("/lecture/" + lecture.getId()));
    }

    @Override
    public LectureDto getById(Long id) {
        Lecture lecture = lectureRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Lecture not found")
        );

        return lectureConverter.convert(lecture);
    }

    @Override
    public List<LectureDto> getAll() {
        List<Lecture> lectures = lectureRepository.findAll();

        return lectures.stream().
                map(lectureConverter::convert).
                collect(Collectors.toList());
    }

    @Override
    public LectureDto update(Long id, CreateLectureDto updateLecture) {
        Lecture lecture = lectureRepository.getById(id);

        lecture.setName(updateLecture.getLectureName());
        lecture.setDate(updateLecture.getLectureDate());
        lecture.setTime(updateLecture.getLectureTime());
        lecture.setTeacher(teacherRepository.getById(updateLecture.getTeacherId()));
        lecture.setAudience(audienceRepository.getById(updateLecture.getAudienceId()));

        lectureRepository.save(lecture);

        return lectureConverter.convert(lecture);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        lectureRepository.deleteById(id);
    }

    @Override
    public List<LectureDto> getLectureForStudent(Long studentId, LocalDate lectureDate) {
        List<Lecture> lectures = lectureRepository.getLectureForStudent(lectureDate, studentId);

        return lectures.stream().
                map(lectureConverter::convert).
                collect(Collectors.toList());

    }
}
