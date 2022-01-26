package com.university.app.service.impl;

import com.university.app.dto.CreateTeacherDto;
import com.university.app.dto.IdLocationDto;
import com.university.app.dto.TeacherDto;
import com.university.app.repository.TeacherRepository;
import com.university.app.repository.domain.Teacher;
import com.university.app.service.TeacherService;
import com.university.app.service.converter.TeacherConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final TeacherConverter teacherConverter;

    @Override
    public IdLocationDto save(CreateTeacherDto createTeacherDto) throws URISyntaxException {
        Teacher teacher = teacherConverter.convert(createTeacherDto);

        teacherRepository.save(teacher);

        return new IdLocationDto(teacher.getId(), new URI("/teacher/" + teacher.getId()));
    }

    @Override
    public TeacherDto getById(Long id) {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Teacher not found")
        );

        return teacherConverter.convert(teacher);
    }

    @Override
    public List<TeacherDto> getAll() {
        List<Teacher> teachers = teacherRepository.findAll();

        return teachers.stream().
                map(teacherConverter::convert).
                collect(Collectors.toList());
    }

    @Override
    public TeacherDto update(Long id, CreateTeacherDto updateTeacher) {
        Teacher teacher = teacherRepository.getById(id);

        teacher.setFirstName(updateTeacher.getFirstName());
        teacher.setLastName(updateTeacher.getLastName());

        teacherRepository.save(teacher);

        return teacherConverter.convert(teacher);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        teacherRepository.deleteById(id);
    }
}
