package com.piotgrochowiecki.eriderent.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor @Builder @EqualsAndHashCode(of = "id")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z]+$")
    private String firstName;
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z]+$") //only letters
    private String lastName;
    @NotBlank @Email
    private String email;
    @NotBlank @Past
    private LocalDate drivingLicenseIssueDate;
    @NotBlank
    private String role;

    @OneToMany(mappedBy = "customer")
    @ToString.Exclude
    private List<ReservationPeriodEntity> reservationPeriodList =
            new ArrayList<>();
    //One customer to many reservation periods

    @OneToMany(mappedBy = "customer")
    @ToString.Exclude
    private List<ReviewEntity> reviewList = new ArrayList<>();
    //One customer to many reviews
}
