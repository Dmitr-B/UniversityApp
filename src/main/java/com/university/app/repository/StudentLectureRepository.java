package com.university.app.repository;

import com.university.app.repository.domain.StudentLecture;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface StudentLectureRepository extends CrudRepository<StudentLecture, Long> {

    @Modifying
    @Query(value = "DELETE FROM student_lecture sl WHERE " +
            "sl.lecture_id = :lectureId AND sl.student_id = :studentId", nativeQuery = true)
    void deleteStudentLecture(@Param("lectureId") Long lectureId, @Param("studentId") Long studentId);
}
