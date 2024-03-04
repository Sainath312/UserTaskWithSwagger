package com.example.UserTask.model;

import com.example.UserTask.constants.StringConstants;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class UserModel {
    @NotBlank(message = StringConstants.USER_NAME)
    public String name;

    @NotBlank(message = StringConstants.USER_FIRST_NAME)
    public String firstName;

    @NotBlank(message = StringConstants.USER_LAST_NAME)
    public String lastName;


    @Email(message =StringConstants.MAIL_REQUIRED)
    @Pattern.List({
            @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Email must be valid format"),
            @Pattern(regexp = ".*@gmail\\.com", message = StringConstants.VALID_MAIL)
    })
    private String emailId;

    @NotBlank(message = StringConstants.MOBILE_NUMBER_REQUIRED)
    @Pattern(regexp = "^[7-9]\\d{9}$", message = StringConstants.VALID_MOBILE_NUMBER)
    public String mobileNumber;

}
