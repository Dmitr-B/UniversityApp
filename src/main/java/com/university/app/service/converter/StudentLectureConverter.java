package com.university.app.service.converter;

import com.university.app.dto.CreateStudentLectureDto;
import com.university.app.dto.StudentLectureDto;
import com.university.app.repository.domain.Lecture;
import com.university.app.repository.domain.StudentLecture;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class StudentLectureConverter {

    private final TeacherConverter teacherConverter;
    private final AudienceConverter audienceConverter;

    public StudentLectureDto convert(Lecture lecture) {
        return new StudentLectureDto(lecture.getName(), lecture.getDate(), lecture.getTime(),
                teacherConverter.convert(lecture.getTeacher()), audienceConverter.convert(lecture.getAudience()));
    }

    public StudentLecture convert(CreateStudentLectureDto createStudentLectureDto) {
        StudentLecture studentLecture = new StudentLecture();
        studentLecture.setStudentId(createStudentLectureDto.getStudentId());
        //studentLecture.setLectureId(createStudentLectureDto.getLectureId());

        return studentLecture;
    }
}
