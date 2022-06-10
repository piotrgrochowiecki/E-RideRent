package com.piotgrochowiecki.eriderent.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Table(name = "reservations")
@Setter @Getter @ToString @Builder @EqualsAndHashCode(of = "id")
@NoArgsConstructor @AllArgsConstructor
public class ReservationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Future
    private LocalDate startDate;

    @NotBlank @Future
    private LocalDate endDate;

    @ManyToOne
    private UserEntity user;
    //many reservation periods to one user

    @ManyToOne
    private CarEntity car;
    //many reservations to one car
}
