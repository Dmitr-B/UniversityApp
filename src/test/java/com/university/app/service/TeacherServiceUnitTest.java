package com.university.app.service;

import com.university.app.dto.CreateTeacherDto;
import com.university.app.dto.IdLocationDto;
import com.university.app.dto.TeacherDto;
import com.university.app.repository.TeacherRepository;
import com.university.app.repository.domain.Teacher;
import com.university.app.service.converter.TeacherConverter;
import com.university.app.service.impl.TeacherServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TeacherServiceUnitTest {

    private static final long TEACHER_ID = 1;
    private static final String TEACHER_FIRST_NAME = "testFirstName";
    private static final String TEACHER_LAST_NAME = "testLastName";

    @InjectMocks
    private TeacherServiceImpl teacherService;
    @Mock
    private TeacherRepository teacherRepository;
    @Mock
    private TeacherConverter teacherConverter;

    @Test
    void saveTeacher_whenSave_theOk() throws URISyntaxException {
        IdLocationDto expected = new IdLocationDto(TEACHER_ID, new URI("/teacher/" + TEACHER_ID));
        Teacher testTeacher = new Teacher();
        testTeacher.setId(TEACHER_ID);
        testTeacher.setFirstName(TEACHER_FIRST_NAME);
        testTeacher.setLastName(TEACHER_LAST_NAME);

        when(teacherConverter.convert((CreateTeacherDto) any())).thenReturn(testTeacher);

        IdLocationDto actual = teacherService.save(new CreateTeacherDto(TEACHER_FIRST_NAME, TEACHER_LAST_NAME));

        verify(teacherRepository).save(testTeacher);

        assertEquals(expected, actual);
    }

    @Test
    void getTeacherById_whenExist_theOk() {
        Teacher testTeacherInDB = new Teacher();
        testTeacherInDB.setId(TEACHER_ID);
        testTeacherInDB.setFirstName(TEACHER_FIRST_NAME);
        testTeacherInDB.setLastName(TEACHER_LAST_NAME);

        TeacherDto expected = new TeacherDto(TEACHER_ID, TEACHER_FIRST_NAME, TEACHER_LAST_NAME);

        when(teacherRepository.findById(TEACHER_ID)).thenReturn(java.util.Optional.of(testTeacherInDB));
        when(teacherConverter.convert(testTeacherInDB)).thenReturn(expected);

        TeacherDto actual = teacherService.getById(TEACHER_ID);

        assertEquals(expected, actual);
    }

    @Test
    void getTeacherById_whenDoesNotExist_thenThrow() {
        String expected = "Teacher not found";

        when(teacherRepository.findById(TEACHER_ID)).thenReturn(Optional.empty());

        IllegalArgumentException actual = assertThrows(IllegalArgumentException.class,
                () -> teacherService.getById(TEACHER_ID));

        assertEquals(expected, actual.getMessage());
    }

    @Test
    void getAllTeachers_whenExist_theOk() {
        List<Teacher> teachersInDB = new ArrayList<>();
        Teacher teacher = new Teacher();
        teacher.setId(TEACHER_ID);
        teacher.setFirstName(TEACHER_FIRST_NAME);
        teacher.setLastName(TEACHER_LAST_NAME);
        teachersInDB.add(teacher);

        List<TeacherDto> expected = new ArrayList<>();
        TeacherDto teacherDto = new TeacherDto(TEACHER_ID, TEACHER_FIRST_NAME, TEACHER_LAST_NAME);
        expected.add(teacherDto);

        when(teacherRepository.findAll()).thenReturn(teachersInDB);
        when(teacherConverter.convert(teacher)).thenReturn(teacherDto);

        List<TeacherDto> actual = teacherService.getAll();

        assertEquals(expected, actual);
    }

    @Test
    void updateTeacher_whenHasTeacherId_thenUpdate() {
        Teacher testTeacherInDB = new Teacher();
        testTeacherInDB.setId(TEACHER_ID);
        testTeacherInDB.setFirstName(TEACHER_FIRST_NAME);
        testTeacherInDB.setLastName(TEACHER_LAST_NAME);

        TeacherDto expected = new TeacherDto(TEACHER_ID, "updatedFirstName", TEACHER_LAST_NAME);

        when(teacherRepository.getById(TEACHER_ID)).thenReturn(testTeacherInDB);
        when(teacherConverter.convert(testTeacherInDB)).thenReturn(expected);

        TeacherDto actual = teacherService.update(TEACHER_ID, new CreateTeacherDto("updatedFirstName", TEACHER_LAST_NAME));

        assertEquals(expected, actual);
    }

    @Test
    void deleteTeacher_whenHasTeacherId_thenDelete() {

        teacherService.delete(TEACHER_ID);

        verify(teacherRepository).deleteById(TEACHER_ID);
    }
}
