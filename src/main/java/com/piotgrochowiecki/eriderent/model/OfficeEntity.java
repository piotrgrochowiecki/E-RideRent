package com.piotgrochowiecki.eriderent.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "offices")
@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class OfficeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String city;

    @NotBlank
    private String street;

    @NotBlank
    private String streetNumber;

    private String additionalInfo;
}
