package com.piotgrochowiecki.eriderent.dto;

import com.piotgrochowiecki.eriderent.model.Role;
import com.piotgrochowiecki.eriderent.validator.PasswordConfirmation;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Setter @Getter @EqualsAndHashCode @ToString
@Builder @AllArgsConstructor @NoArgsConstructor
@PasswordConfirmation(password = "password", matchingPassword = "matchingPassword")
public class UserDto {

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z]+$", message = "{validation.error.patternOnlyLetters}") //only letter
    private String firstName;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z]+$", message = "{validation.error.patternOnlyLetters}") //only letters
    private String lastName;

    @Email
    private String email;

    @NotNull
    @Past
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate drivingLicenseIssueDate;

    @NotBlank
    private String password;

    private String matchingPassword;

    private Role role;

}
