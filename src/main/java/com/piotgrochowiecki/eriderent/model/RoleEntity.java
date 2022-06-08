package com.piotgrochowiecki.eriderent.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
@Setter @Getter @ToString @AllArgsConstructor @NoArgsConstructor
@Builder @EqualsAndHashCode(of = "id")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank @Pattern(regexp = "user|admin", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String name;

    @ManyToMany(mappedBy = "roleList")
    @ToString.Exclude
    private List<UserEntity> userList = new ArrayList<>();
}
