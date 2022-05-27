package com.piotgrochowiecki.eriderent.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor @Builder
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank @Email
    private String email;
    @NotBlank
    private LocalDate drivingLicenseIssueDate;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<ReservationPeriodEntity> reservationPeriodList =
            new ArrayList<>();
    //One customer to many reservation periods

    @OneToMany(mappedBy = "customer")
    private List<ReviewEntity> reviewList = new ArrayList<>();
    //One customer to many reviews
}
