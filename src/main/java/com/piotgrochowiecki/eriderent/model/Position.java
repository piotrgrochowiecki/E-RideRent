package com.piotgrochowiecki.eriderent.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "position")
@Setter
@Getter
@Builder
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDateTime time;

    @NotNull
    private Double longitude;

    @NotNull
    private Double latitude;

    @ManyToOne
    private Car car;
}
