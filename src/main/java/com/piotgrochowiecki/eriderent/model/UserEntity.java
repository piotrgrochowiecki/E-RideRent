package com.piotgrochowiecki.eriderent.model;

import com.piotgrochowiecki.eriderent.validator.PasswordConfirmation;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor @Builder @EqualsAndHashCode(of = "id")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z]+$")
    private String firstName;
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z]+$") //only letters
    private String lastName;
    @NotBlank @Email @Column(unique = true)
    private String email;
    @NotBlank @Past @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate drivingLicenseIssueDate;
    @NotBlank
    private String password;
    @PasswordConfirmation
    private String matchingPassword;
    @NotBlank
    private String role;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<ReservationEntity> reservationList =
            new ArrayList<>();
    //One user to many reservation periods

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<ReviewEntity> reviewList = new ArrayList<>();
    //One user to many reviews
}
