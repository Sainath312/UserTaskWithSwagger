package com.example.UserTask.repository;

import com.example.UserTask.entity.Session;
import org.springframework.data.repository.CrudRepository;



public interface SessionRepo extends CrudRepository<Session,Long> {
    Session findByAdminId(Long id);
}
