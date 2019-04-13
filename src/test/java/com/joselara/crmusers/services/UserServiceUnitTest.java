package com.joselara.crmusers.services;

import com.joselara.crmusers.converters.UserConverter;
import com.joselara.crmusers.models.User;
import com.joselara.crmusers.models.dto.UserDTO;
import com.joselara.crmusers.models.enums.UserRole;
import com.joselara.crmusers.repositories.UserRepository;
import javassist.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceUnitTest {

    @InjectMocks
    private UserService cut;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserConverter userConverter;

    private User user;

    @Before
    public void setUp() {
        user = new User();
        user.setEmail("test@domain.com");
        user.setSecret("testPassword");
        user.setUserRole(UserRole.ROLE_USER);

        when(userRepository.findById(any())).thenReturn(Optional.of(user));
    }

    @Test
    public void createUserTest() {
        UserDTO userDTO = UserDTO.builder()
                .email("test@domain.com")
                .secret("testPassword")
                .userRole(UserRole.ROLE_USER)
                .build();
        when(userConverter.map(userDTO, User.class)).thenReturn(user);

        User actualUser = cut.createUser(userDTO);

        assertEquals(actualUser.getEmail(), userDTO.getEmail());
    }

    @Test
    public void deleteUserTest() throws NotFoundException {
        cut.deleteUser(any());

        verify(userRepository, times(1)).delete(any());
    }

    @Test
    public void updateUserTest() throws NotFoundException {
        UserDTO userDTO = UserDTO.builder()
                .email("new@domain.com")
                .secret("newPassword")
                .userRole(UserRole.ROLE_USER)
                .build();

        User actualUser = cut.updateUser(userDTO, user.getEmail());

        assertEquals(user.getSecret(), actualUser.getSecret());
    }

    @Test(expected = NotFoundException.class)
    public void updateUserFailsTest() throws NotFoundException {
        when(userRepository.findById(user.getEmail())).thenReturn(Optional.empty());
        UserDTO userDTO = UserDTO.builder()
                .email("new@domain.com")
                .secret("newPassword")
                .userRole(UserRole.ROLE_USER)
                .build();

        cut.updateUser(userDTO, user.getEmail());
    }

    @Test
    public void updateUserRoleTest() throws NotFoundException {
        UserDTO userDTO = UserDTO.builder()
                .email("new@domain.com")
                .secret("newPassword")
                .userRole(UserRole.ROLE_ADMIN)
                .build();

        User actualUser = cut.updateUser(userDTO, user.getEmail());

        assertEquals(user.getEmail(), actualUser.getEmail());
    }

    @Test
    public void findUserTest() {
        List<User> userList = List.of(user);
        when(userRepository.findAll()).thenReturn(userList);

        List<User> actualList = cut.findAllUsers();

        assertEquals(userList, actualList);
        assertEquals(1, actualList.size());
    }
}
