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
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z]+$")
    private String firstName;
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z]+$") //only letters
    private String lastName;
    @Email
    @Column(unique = true) //dodać własną walidację czy email już nie istnieje, bo do @Column nie można dodać atrybutu message
    private String email;
    @NotBlank @Past @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate drivingLicenseIssueDate;
    @NotBlank
    private String password;
    @PasswordConfirmation
    private String matchingPassword;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<Reservation> reservationList =
            new ArrayList<>();
    //One user to many reservation periods

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<Review> reviewList = new ArrayList<>();
    //One user to many reviews

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    @ToString.Exclude
    private List<Role> roleList = new ArrayList<>();
}
