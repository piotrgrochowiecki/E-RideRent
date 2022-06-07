package com.piotgrochowiecki.eriderent.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Table(name = "reservation_periods")
@Setter @Getter @ToString @Builder @EqualsAndHashCode(of = "id")
@NoArgsConstructor @AllArgsConstructor
public class ReservationPeriodEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private LocalDate startDate;

    @NotBlank
    private LocalDate endDate;

    @ManyToOne
    private UserEntity customer;
    //many reservation periods to one customer

    @ManyToOne
    private CarEntity car;
    //many reservation periods to one car
}
