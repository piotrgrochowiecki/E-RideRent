package com.piotgrochowiecki.eriderent.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity(name = "admins")
@Getter @Setter @ToString @Builder @EqualsAndHashCode(of = "id")
@NoArgsConstructor @AllArgsConstructor
public class AdminEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    @Pattern(regexp = "admin", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String role;

}
