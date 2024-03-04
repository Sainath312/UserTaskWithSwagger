package com.example.UserTask.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public long id;

    public String userName;

    public String password;

    public String email;

    public String roles="ROLE_ADMIN";

    public AdminEntity(String userName, String email, String password) {
    this.userName = userName;
    this.email = email;
    this.password = password;
    }

}
