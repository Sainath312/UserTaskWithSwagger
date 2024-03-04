package com.example.UserTask.repository;

import com.example.UserTask.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {


    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByName(String userName);

    @Query("SELECT u FROM UserEntity u WHERE LOWER(u.name) LIKE LOWER(concat('%', :searchString, '%')) " +
            "OR LOWER(u.firstName) LIKE LOWER(concat('%', :searchString, '%')) " +
            "OR LOWER(u.lastName) LIKE LOWER(concat('%', :searchString, '%')) " +
            "OR LOWER(u.email) LIKE LOWER(concat('%', :searchString, '%')) " +
            "OR LOWER(u.mobileNumber) LIKE LOWER(concat('%', :searchString, '%'))")
    List<UserEntity> findByAnyDetail(String searchString);

}


