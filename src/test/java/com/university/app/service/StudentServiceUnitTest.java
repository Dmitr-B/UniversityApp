package com.university.app.service;

import com.university.app.dto.CreateStudentDto;
import com.university.app.dto.IdLocationDto;
import com.university.app.dto.StudentDto;
import com.university.app.repository.StudentRepository;
import com.university.app.repository.domain.Student;
import com.university.app.service.converter.StudentConverter;
import com.university.app.service.impl.StudentServiceImpl;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentServiceUnitTest {

    private static final long STUDENT_ID = 1;
    private static final String STUDENT_FIRST_NAME = "testFirstName";
    private static final String STUDENT_LAST_NAME = "testLastName";

    @InjectMocks
    private StudentServiceImpl studentService;
    @Mock
    private StudentRepository studentRepository;
    @Mock
    private StudentConverter studentConverter;



    @Test
    void saveStudent_whenSave_theOk() throws URISyntaxException {
        IdLocationDto expected = new IdLocationDto(STUDENT_ID, new URI("/student/" + STUDENT_ID));
        Student testStudent = new Student();
        testStudent.setId(STUDENT_ID);
        testStudent.setFirstName(STUDENT_FIRST_NAME);
        testStudent.setLastName(STUDENT_LAST_NAME);

        when(studentConverter.convert((CreateStudentDto) any())).thenReturn(testStudent);

        IdLocationDto actual = studentService.save(new CreateStudentDto(STUDENT_FIRST_NAME, STUDENT_LAST_NAME));

        verify(studentRepository).save(testStudent);
        assertEquals(expected, actual);
    }

    @Test
    void getStudentById_whenExist_theOk() {
        Student testStudentInDB = new Student();
        testStudentInDB.setId(STUDENT_ID);
        testStudentInDB.setFirstName(STUDENT_FIRST_NAME);
        testStudentInDB.setLastName(STUDENT_LAST_NAME);

        StudentDto expected = new StudentDto(STUDENT_ID, STUDENT_FIRST_NAME, STUDENT_LAST_NAME);

        when(studentRepository.findById(STUDENT_ID)).thenReturn(java.util.Optional.of(testStudentInDB));
        when(studentConverter.convert(testStudentInDB)).thenReturn(expected);

        StudentDto actual = studentService.getById(STUDENT_ID);

        assertEquals(expected, actual);
    }

    @Test
    void getStudentById_whenDoesNotExist_thenThrow() {
        String expected = "Student not found";

        when(studentRepository.findById(STUDENT_ID)).thenReturn(Optional.empty());

        IllegalArgumentException actual = assertThrows(IllegalArgumentException.class,
                () -> studentService.getById(STUDENT_ID));

        assertEquals(expected, actual.getMessage());
    }

    @Test
    void getAllStudents_whenExist_theOk() {
        List<Student> studentsInDB = new ArrayList<>();
        Student student = new Student();
        student.setId(STUDENT_ID);
        student.setFirstName(STUDENT_FIRST_NAME);
        student.setLastName(STUDENT_LAST_NAME);
        studentsInDB.add(student);

        List<StudentDto> expected = new ArrayList<>();
        StudentDto studentDto = new StudentDto(STUDENT_ID, STUDENT_FIRST_NAME, STUDENT_LAST_NAME);
        expected.add(studentDto);

        when(studentRepository.findAll()).thenReturn(studentsInDB);
        when(studentConverter.convert(student)).thenReturn(studentDto);

        List<StudentDto> actual = studentService.getAll();

        assertEquals(expected, actual);
    }

    @Test
    void updateStudent_whenHasStudentId_thenUpdate() {
        Student testStudentInDB = new Student();
        testStudentInDB.setId(STUDENT_ID);
        testStudentInDB.setFirstName(STUDENT_FIRST_NAME);
        testStudentInDB.setLastName(STUDENT_LAST_NAME);

        StudentDto expected = new StudentDto(STUDENT_ID, "updatedFirstName", STUDENT_LAST_NAME);

        when(studentRepository.getById(STUDENT_ID)).thenReturn(testStudentInDB);
        when(studentConverter.convert(testStudentInDB)).thenReturn(expected);

        StudentDto actual = studentService.update(STUDENT_ID, new CreateStudentDto("updatedFirstName", STUDENT_LAST_NAME));

        assertEquals(expected, actual);
    }

    @Test
    void deleteStudent_whenHasStudentId_thenDelete() {
        studentService.delete(STUDENT_ID);

        verify(studentRepository).deleteById(STUDENT_ID);
    }
}
