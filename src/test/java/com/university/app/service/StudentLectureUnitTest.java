package com.university.app.service;

import com.university.app.dto.CreateStudentLectureDto;
import com.university.app.repository.StudentLectureRepository;
import com.university.app.repository.domain.StudentLecture;
import com.university.app.service.converter.StudentLectureConverter;
import com.university.app.service.impl.StudentLectureServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StudentLectureUnitTest {

    private static final long STUDENT_ID = 1;
    private static final long LECTURE_ID = 1;

    @InjectMocks
    private StudentLectureServiceImpl studentLectureService;
    @Mock
    private StudentLectureRepository studentLectureRepository;
    @Mock
    private StudentLectureConverter studentLectureConverter;

    @Test
    void saveStudentLecture_whenSave_theOk() {
        StudentLecture testStudentLecture = new StudentLecture();
        testStudentLecture.setStudentId(STUDENT_ID);
        testStudentLecture.setLectureId(LECTURE_ID);

        when(studentLectureConverter.convert((CreateStudentLectureDto) any())).thenReturn(testStudentLecture);

        studentLectureService.save(LECTURE_ID, new CreateStudentLectureDto(LECTURE_ID));

        verify(studentLectureRepository).save(testStudentLecture);
    }

    @Test
    void deleteStudentLecture_whenHasStudentIdAndLectureId_thenDelete() {

        studentLectureService.delete(LECTURE_ID, STUDENT_ID);

        verify(studentLectureRepository).deleteStudentLecture(LECTURE_ID, STUDENT_ID);
    }
}
