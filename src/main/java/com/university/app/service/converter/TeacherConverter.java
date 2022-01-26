package com.university.app.service.converter;

import com.university.app.dto.CreateTeacherDto;
import com.university.app.dto.TeacherDto;
import com.university.app.repository.domain.Teacher;
import org.springframework.stereotype.Component;

@Component
public class TeacherConverter {

    public TeacherDto convert(Teacher teacher) {
        return new TeacherDto(teacher.getId(), teacher.getFirstName(), teacher.getLastName());
    }

    public Teacher convert(CreateTeacherDto createTeacherDto) {
        Teacher teacher = new Teacher();
        teacher.setFirstName(createTeacherDto.getFirstName());
        teacher.setLastName(createTeacherDto.getLastName());
        return teacher;
    }

    public Teacher convert(TeacherDto teacherDto) {
        Teacher teacher = new Teacher();
        teacher.setId(teacherDto.getId());
        teacher.setFirstName(teacherDto.getFirstName());
        teacher.setLastName(teacherDto.getLastName());
        return teacher;
    }
}
