package com.piotgrochowiecki.eriderent.dto.request;

import com.piotgrochowiecki.eriderent.dto.mapper.BaseMapper;
import com.piotgrochowiecki.eriderent.model.enumerator.Role;
import com.piotgrochowiecki.eriderent.model.User;
import com.piotgrochowiecki.eriderent.validator.PasswordConfirmation;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Setter
@Getter
@EqualsAndHashCode
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@PasswordConfirmation(password = "password", matchingPassword = "matchingPassword")
public class UserRegisterRequestDto extends BaseMapper {

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z]+$", message = "{validation.error.patternOnlyLetters}") //only letter
    String firstName;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z]+$", message = "{validation.error.patternOnlyLetters}") //only letters
    String lastName;

    @Email
    String email;

    @NotNull
    @Past
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate drivingLicenseIssueDate;

    @NotBlank
    String password;

    String matchingPassword;

    @Enumerated(EnumType.STRING)
    Role role;

    public static User map(UserRegisterRequestDto user) {
        return map(user, User.class);
    }

}
