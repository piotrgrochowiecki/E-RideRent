package com.piotgrochowiecki.eriderent.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "reviews")
@Setter @Getter @ToString @Builder @EqualsAndHashCode(of = "id")
@NoArgsConstructor @AllArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Size(max = 3000)
    private String review;

    @ManyToOne
    private Car car;

    @ManyToOne
    private User user;
    //Many reviews to one user
}
