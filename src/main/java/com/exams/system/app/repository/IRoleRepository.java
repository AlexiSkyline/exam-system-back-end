package com.exams.system.app.repository;

import com.exams.system.app.models.Role;
import com.exams.system.app.models.TypeRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName( TypeRole name );
}