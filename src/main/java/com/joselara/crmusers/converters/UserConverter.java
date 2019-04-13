package com.joselara.crmusers.converters;

import com.joselara.crmusers.models.User;
import com.joselara.crmusers.models.dto.UserDTO;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

@Component
public class UserConverter extends ConfigurableMapper {

    @Override
    protected void configure(MapperFactory factory) {

        factory.classMap(UserDTO.class, User.class)
                .field("email", "email")
                .field("secret", "secret")
                .field("userRole", "userRole")
                .byDefault()
                .register();
    }
}
