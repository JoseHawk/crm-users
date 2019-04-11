package com.joselara.crmusers.models;

import com.joselara.crmusers.models.enums.UserRole;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "authenticated_user")
public class User {

    @Id
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;
}
