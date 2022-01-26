package com.university.app.repository;

import com.university.app.repository.domain.Teacher;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TeacherRepository extends CrudRepository<Teacher, Long> {

    Optional<Teacher> findById(Long id);

    @Query(value = "SELECT t FROM Teacher t WHERE t.id = :id")
    Teacher getById(@Param("id") Long id);

    List<Teacher> findAll();

    @Modifying
    @Query(value = "DELETE FROM teacher t WHERE t.id = ?" , nativeQuery = true)
    void deleteById(Long id);
}
