package com.university.app.service;

import com.university.app.dto.*;
import com.university.app.repository.AudienceRepository;
import com.university.app.repository.LectureRepository;
import com.university.app.repository.TeacherRepository;
import com.university.app.repository.domain.Audience;
import com.university.app.repository.domain.Lecture;
import com.university.app.repository.domain.Teacher;
import com.university.app.service.converter.LectureConverter;
import com.university.app.service.impl.LectureServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LectureServiceUnitTest {

    private static final long STUDENT_ID = 1;
    private static final long LECTURE_ID = 1;
    private static final String LECTURE_NAME = "testLectureName";
    private static final LocalDate LECTURE_DATE = LocalDate.of(2022, 1, 25);
    private static final LocalTime LECTURE_TIME = LocalTime.of(13, 0,0);
    private static final long TEACHER_ID = 1;
    private static final String TEACHER_FIRST_NAME = "testTeacherFirstName";
    private static final String TEACHER_LAST_NAME = "testTeacherLastName";
    private static final long AUDIENCE_ID = 1;
    private static final String AUDIENCE_NAME = "testAudienceName";

    @InjectMocks
    private LectureServiceImpl lectureService;
    @Mock
    private LectureRepository lectureRepository;
    @Mock
    private LectureConverter lectureConverter;
    @Mock
    private TeacherRepository teacherRepository;
    @Mock
    private AudienceRepository audienceRepository;

    @Test
    void saveLecture_whenSave_theOk() throws URISyntaxException {
        IdLocationDto expected = new IdLocationDto(LECTURE_ID, new URI("/lecture/" + LECTURE_ID));
        Lecture testLecture = new Lecture();
        testLecture.setId(LECTURE_ID);
        testLecture.setName(LECTURE_NAME);
        testLecture.setDate(LECTURE_DATE);
        testLecture.setTime(LECTURE_TIME);

        when(lectureConverter.convert((CreateLectureDto) any())).thenReturn(testLecture);

        IdLocationDto actual = lectureService.save(new CreateLectureDto(LECTURE_NAME, LECTURE_DATE, LECTURE_TIME, TEACHER_ID, AUDIENCE_ID));

        verify(lectureRepository).save(testLecture);

        assertEquals(expected, actual);
    }

    @Test
    void getLectureById_whenExist_theOk() {
        Lecture testLectureInDB = new Lecture();
        testLectureInDB.setId(LECTURE_ID);
        testLectureInDB.setName(LECTURE_NAME);
        testLectureInDB.setDate(LECTURE_DATE);
        testLectureInDB.setTime(LECTURE_TIME);

        LectureDto expected = new LectureDto(LECTURE_ID, LECTURE_NAME, LECTURE_DATE, LECTURE_TIME,
                new TeacherDto(TEACHER_ID, TEACHER_FIRST_NAME, TEACHER_LAST_NAME), new AudienceDto(AUDIENCE_ID, AUDIENCE_NAME));

        when(lectureRepository.findById(LECTURE_ID)).thenReturn(Optional.of(testLectureInDB));
        when(lectureConverter.convert(testLectureInDB)).thenReturn(expected);

        LectureDto actual = lectureService.getById(LECTURE_ID);

        assertEquals(expected, actual);
    }

    @Test
    void getLectureById_whenDoesNotExist_thenThrow() {
        String expected = "Lecture not found";

        when(lectureRepository.findById(LECTURE_ID)).thenReturn(Optional.empty());

        IllegalArgumentException actual = assertThrows(IllegalArgumentException.class,
                () -> lectureService.getById(LECTURE_ID));

        assertEquals(expected, actual.getMessage());
    }

    @Test
    void getAllLectures_whenExist_theOk() {
        List<Lecture> lecturesInDB = new ArrayList<>();
        Lecture lecture = new Lecture();
        lecture.setId(LECTURE_ID);
        lecture.setName(LECTURE_NAME);
        lecture.setDate(LECTURE_DATE);
        lecture.setTime(LECTURE_TIME);
        lecturesInDB.add(lecture);

        List<LectureDto> expected = new ArrayList<>();
        LectureDto lectureDto = new LectureDto(LECTURE_ID, LECTURE_NAME, LECTURE_DATE, LECTURE_TIME,
                new TeacherDto(TEACHER_ID, TEACHER_FIRST_NAME, TEACHER_LAST_NAME), new AudienceDto(AUDIENCE_ID, AUDIENCE_NAME));
        expected.add(lectureDto);

        when(lectureRepository.findAll()).thenReturn(lecturesInDB);
        when(lectureConverter.convert(lecture)).thenReturn(lectureDto);

        List<LectureDto> actual = lectureService.getAll();

        assertEquals(expected, actual);
    }

    @Test
    void getAllLecturesForStudent_whenExist_theOk() {
        List<Lecture> lecturesInDB = new ArrayList<>();
        Lecture lecture = new Lecture();
        lecture.setId(LECTURE_ID);
        lecture.setName(LECTURE_NAME);
        lecture.setDate(LECTURE_DATE);
        lecture.setTime(LECTURE_TIME);
        lecturesInDB.add(lecture);

        List<LectureDto> expected = new ArrayList<>();
        LectureDto lectureDto = new LectureDto(LECTURE_ID, LECTURE_NAME, LECTURE_DATE, LECTURE_TIME,
                new TeacherDto(TEACHER_ID, TEACHER_FIRST_NAME, TEACHER_LAST_NAME), new AudienceDto(AUDIENCE_ID, AUDIENCE_NAME));
        expected.add(lectureDto);

        when(lectureRepository.getLectureForStudent(LECTURE_DATE, STUDENT_ID)).thenReturn(lecturesInDB);
        when(lectureConverter.convert(lecture)).thenReturn(lectureDto);

        List<LectureDto> actual = lectureService.getLectureForStudent(STUDENT_ID, LECTURE_DATE);

        assertEquals(expected, actual);
    }

    @Test
    void updateLecture_whenHasStudentId_thenUpdate() {
        Lecture testLectureInDB = new Lecture();
        testLectureInDB.setId(LECTURE_ID);
        testLectureInDB.setName(LECTURE_NAME);
        testLectureInDB.setDate(LECTURE_DATE);
        testLectureInDB.setTime(LECTURE_TIME);
        testLectureInDB.setTeacher(new Teacher());
        testLectureInDB.setAudience(new Audience());

        LectureDto expected = new LectureDto(LECTURE_ID, "updatedLectureName", LECTURE_DATE, LECTURE_TIME,
                new TeacherDto(TEACHER_ID, TEACHER_FIRST_NAME, TEACHER_LAST_NAME), new AudienceDto(AUDIENCE_ID, AUDIENCE_NAME));

        when(lectureRepository.getById(LECTURE_ID)).thenReturn(testLectureInDB);
        when(lectureConverter.convert(testLectureInDB)).thenReturn(expected);
        when(teacherRepository.getById(TEACHER_ID)).thenReturn(new Teacher());
        when(audienceRepository.getById(AUDIENCE_ID)).thenReturn(new Audience());

        LectureDto actual = lectureService.update(LECTURE_ID, new CreateLectureDto("updatedLectureName", LECTURE_DATE, LECTURE_TIME, TEACHER_ID, AUDIENCE_ID));

        assertEquals(expected, actual);
    }

    @Test
    void deleteLecture_whenHasLectureId_thenDelete() {

        lectureService.delete(LECTURE_ID);

        verify(lectureRepository).deleteById(LECTURE_ID);
    }
}
