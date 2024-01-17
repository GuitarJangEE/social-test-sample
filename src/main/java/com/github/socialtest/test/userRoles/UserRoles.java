package com.github.socialtest.test.userRoles;

import com.github.socialtest.test.users.UserEntity;
import jakarta.persistence.*;
import lombok.*;


@Table(name = "user_roles")
@Getter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class UserRoles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_roles_id")
    private Integer userRolesId;
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "role_id",nullable = false)
    private Roles roles;
}
