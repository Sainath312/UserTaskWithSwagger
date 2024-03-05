package com.example.UserTask.model;

import com.example.UserTask.constants.StringConstants;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {
	@NotEmpty(message = StringConstants.ADMIN_NAME)
	public String adminName;

	@Column
	@NotBlank(message = StringConstants.VALID_PASSWORD)
	@Size(min = 8, message =StringConstants.PASSWORD_LENGTH)
	@Pattern.List({
			@Pattern(regexp = ".*[A-Z].*", message =StringConstants.UPPER_CASE),
			@Pattern(regexp = ".*[a-z].*", message = StringConstants.LOWER_CASE),
			@Pattern(regexp = ".*\\d.*", message = StringConstants.NUMERIC),
			@Pattern(regexp = ".*[@#$%^&+=].*", message =StringConstants.SPECIAL_CHARACTER) })
	public String password;

}