package com.joselara.crmusers.models.dto;

import com.joselara.crmusers.models.enums.UserRole;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {

    @NotNull
    @UniqueElements
    @Email
    private String email;
    @NotNull
    private String secret;
    @NotNull
    private UserRole userRole;

    @Tolerate
    public UserDTO() { }
}
