package com.joselara.crmusers.converters;


import com.joselara.crmusers.models.User;
import com.joselara.crmusers.models.dto.UserDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class UserConverterUnitTest {

    @InjectMocks
    private UserConverter cut;

    @Test
    public void mapTest() {
        UserDTO userDTO = UserDTO.builder()
                .email("test@domain.com")
                .password("passwordTest")
                .build();

        User user = cut.map(userDTO, User.class);

        assertEquals(user.getEmail(), userDTO.getEmail());
    }
}
