package com.piotgrochowiecki.eriderent.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Table(name = "reservations")
@Setter @Getter @ToString @Builder @EqualsAndHashCode(of = "id")
@NoArgsConstructor @AllArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Future @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @NotBlank @Future @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @ManyToOne
    private User user;
    //many reservation periods to one user

    @ManyToOne
    private Car car;
    //many reservations to one car
}
