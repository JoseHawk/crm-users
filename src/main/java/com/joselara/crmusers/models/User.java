package com.joselara.crmusers.models;

import com.joselara.crmusers.models.enums.UserRole;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Data
@Table(name = "authenticated_user")
public class User {

    @Id
    @Email
    private String email;

    private String secret;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;
}
