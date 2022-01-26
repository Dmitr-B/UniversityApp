package com.university.app.repository;

import com.university.app.repository.domain.Student;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {

    Optional<Student> findById(Long id);

    @Query(value = "SELECT s FROM Student s WHERE s.id = :id")
    Student getById(@Param("id") Long id);

    List<Student> findAll();

    @Modifying
    @Query(value = "DELETE FROM student s WHERE s.id = :id", nativeQuery = true)
    void deleteById(@Param("id") Long id);

}
