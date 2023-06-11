package com.piotgrochowiecki.eriderent.model;

import com.piotgrochowiecki.eriderent.model.enumerator.Role;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate drivingLicenseIssueDate;

    private String password;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "{validation.error.notNull}")
    private Role role;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<Reservation> reservationList =
            new ArrayList<>();
    //One user to many reservation periods

}
