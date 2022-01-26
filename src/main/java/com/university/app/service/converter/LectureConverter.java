package com.university.app.service.converter;

import com.university.app.dto.CreateLectureDto;
import com.university.app.dto.LectureDto;
import com.university.app.repository.domain.Lecture;
import com.university.app.service.AudienceService;
import com.university.app.service.TeacherService;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class LectureConverter {

    private final TeacherService teacherService;
    private final AudienceService audienceService;
    private final TeacherConverter teacherConverter;
    private final AudienceConverter audienceConverter;

    public LectureDto convert(Lecture lecture) {
        return new LectureDto(lecture.getId(), lecture.getName(), lecture.getDate(), lecture.getTime(),
                teacherConverter.convert(lecture.getTeacher()), audienceConverter.convert(lecture.getAudience()));
    }

    public Lecture convert(CreateLectureDto createLectureDto) {
        Lecture lecture = new Lecture();
        lecture.setName(createLectureDto.getLectureName());
        lecture.setDate(createLectureDto.getLectureDate());
        lecture.setTime(createLectureDto.getLectureTime());
        lecture.setTeacher(teacherConverter.convert(teacherService.getById(createLectureDto.getTeacherId())));
        lecture.setAudience(audienceConverter.convert(audienceService.getById(createLectureDto.getAudienceId())));

        return lecture;
    }
}
