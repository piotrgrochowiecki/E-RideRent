package com.piotgrochowiecki.eriderent.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor @Builder @EqualsAndHashCode(of = "id")
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

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<Reservation> reservationList =
            new ArrayList<>();
    //One user to many reservation periods

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<Review> reviewList = new ArrayList<>();
    //One user to many reviews

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    @ToString.Exclude
    private List<Role> roleList = new ArrayList<>();
}
