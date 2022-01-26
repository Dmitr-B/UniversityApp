package com.university.app.service.impl;

import com.university.app.dto.CreateStudentDto;
import com.university.app.dto.IdLocationDto;
import com.university.app.dto.StudentDto;
import com.university.app.repository.StudentRepository;
import com.university.app.repository.domain.Student;
import com.university.app.service.StudentService;
import com.university.app.service.converter.StudentConverter;
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
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentConverter studentConverter;

    @Override
    public IdLocationDto save(CreateStudentDto createStudentDto) throws URISyntaxException {
        Student student = studentConverter.convert(createStudentDto);

        studentRepository.save(student);

        return new IdLocationDto(student.getId(), new URI("/student/" + student.getId()));
    }

    @Override
    public StudentDto getById(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Student not found")
        );

        return studentConverter.convert(student);
    }

    @Override
    public List<StudentDto> getAll() {
        List<Student> students = studentRepository.findAll();

        return students.stream().
                map(studentConverter::convert).
                collect(Collectors.toList());
    }

    @Override
    public StudentDto update(Long id, CreateStudentDto updateStudent) {
        Student student = studentRepository.getById(id);

        student.setFirstName(updateStudent.getFirstName());
        student.setLastName(updateStudent.getLastName());

        studentRepository.save(student);

        return studentConverter.convert(student);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        studentRepository.deleteById(id);
    }
}
