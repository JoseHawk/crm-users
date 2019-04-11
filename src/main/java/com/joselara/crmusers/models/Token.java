package com.joselara.crmusers.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "oauth_access_token")
public class Token {

    @Id
    private String authenticationId;

    private String tokenId;

    private byte[] token;

    private String userName;

    private String clientId;

    private byte[] authentication;

    private String refreshToken;
}
