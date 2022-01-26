package com.university.app.repository;

import com.university.app.repository.domain.Audience;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AudienceRepository extends CrudRepository<Audience, Long> {

    Optional<Audience> findById(Long id);

    @Query(value = "SELECT a FROM Audience a WHERE a.id = :id")
    Audience getById(@Param("id") Long id);

    List<Audience> findAll();

    @Modifying
    @Query(value = "DELETE FROM audience a WHERE a.id = :id", nativeQuery = true)
    void deleteById(@Param("id") Long id);
}
