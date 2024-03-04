package com.example.UserTask.entity;

import com.example.UserTask.constants.StringConstants;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public long id;

    @Column(unique = true,nullable = false)
    @NotBlank(message = StringConstants.USER_NAME)
    public String name;

    @Column(nullable = false)
    public String firstName;

    @Column(nullable = false)
    public String lastName;

    @Column(unique = true,nullable = false)
    @NotBlank(message = StringConstants.MAIL_REQUIRED)
    public String email;

    @Column(unique = true,nullable = false)
    @NotBlank(message = StringConstants.MOBILE_NUMBER_REQUIRED)
    public String mobileNumber;

    public UserEntity(String name, String firstName, String lastName, String email, String mobileNumber) {
        this.name = name;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobileNumber = mobileNumber;
    }

}
