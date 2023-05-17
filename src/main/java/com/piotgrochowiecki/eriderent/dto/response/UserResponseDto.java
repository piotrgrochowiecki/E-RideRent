package com.piotgrochowiecki.eriderent.dto.response;

import com.piotgrochowiecki.eriderent.dto.mapper.BaseMapper;
import com.piotgrochowiecki.eriderent.model.Role;
import com.piotgrochowiecki.eriderent.model.User;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Setter
@Getter
@EqualsAndHashCode
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponseDto extends BaseMapper {

    Long id;

    String firstName;

    String lastName;

    String email;

    LocalDate drivingLicenseIssueDate;

    Role role;

    public static UserResponseDto map(User user) {
        return map(user, UserResponseDto.class);
    }

}
