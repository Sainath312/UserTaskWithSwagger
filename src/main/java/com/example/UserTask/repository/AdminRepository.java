package com.example.UserTask.repository;

import com.example.UserTask.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<AdminEntity,Long> {

    Optional<AdminEntity> findByUserName(String userName);

    Optional<AdminEntity> findByEmail(String email);
}
