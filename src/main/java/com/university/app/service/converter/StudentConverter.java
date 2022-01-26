package com.university.app.service.converter;

import com.university.app.dto.CreateStudentDto;
import com.university.app.dto.StudentDto;
import com.university.app.repository.domain.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentConverter {

    public StudentDto convert(Student student) {
        return new StudentDto(student.getId(), student.getFirstName(), student.getLastName());
    }

    public Student convert(CreateStudentDto createStudentDto) {
        Student student = new Student();
        student.setFirstName(createStudentDto.getFirstName());
        student.setLastName(createStudentDto.getLastName());
        return student;
    }
}
