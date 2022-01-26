package com.university.app.repository;

import com.university.app.repository.domain.Lecture;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface LectureRepository extends CrudRepository<Lecture, Long> {

    Optional<Lecture> findById(Long id);

    @Query(value = "SELECT l FROM Lecture l WHERE l.id = :id")
    Lecture getById(@Param("id") Long id);

    List<Lecture> findAll();

    @Modifying
    @Query(value = "DELETE FROM lecture l WHERE l.id = ?" , nativeQuery = true)
    void deleteById(Long id);


    @Query(value = "select l from Lecture l\n" +
            "    inner join StudentLecture sl on l.id = sl.lectureId\n" +
            "where l.date = :date and sl.studentId = :id")
    List<Lecture> getLectureForStudent(@Param("date") LocalDate date, @Param("id") Long id);
}
