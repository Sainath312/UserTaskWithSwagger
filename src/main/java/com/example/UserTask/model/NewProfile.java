package com.example.UserTask.model;

import com.example.UserTask.constants.StringConstants;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NewProfile {

    @Column(unique = true)
    @NotBlank(message = "UserName cannot be blank")
    public String userName;

    @NotBlank(message = "Password is required")
    @Email(message = "Enter Valid Email")
    @Pattern.List({
            @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Email must be valid format"),
            @Pattern(regexp = ".*@gmail\\.com", message = StringConstants.VALID_MAIL)
    })public String email;

    @Column
    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern.List({
            @Pattern(regexp = ".*[A-Z].*", message = "Password must contain at least one UpperCase Character"),
            @Pattern(regexp = ".*[a-z].*", message = "Password must contain at least one LowerCase Character"),
            @Pattern(regexp = ".*\\d.*", message = "Password must contain at least one number "),
            @Pattern(regexp = ".*[@#$%^&+=].*", message = "Password must contain at least one special character") })
    public String password;

}

