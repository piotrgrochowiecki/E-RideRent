package com.piotgrochowiecki.eriderent.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "reviews")
@Setter @Getter @ToString @Builder
@NoArgsConstructor @AllArgsConstructor
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Size(max = 3000)
    private String review;

    @ManyToOne
    private CarEntity car;

    @ManyToOne
    private CustomerEntity customer;
    //Many reviews to one customer
}
