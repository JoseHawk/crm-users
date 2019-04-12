package com.joselara.crmusers.controllers;

import com.joselara.crmusers.models.User;
import com.joselara.crmusers.services.UserService;
import javassist.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerUnitTest {

    @InjectMocks
    private UserController cut;

    @Mock
    private UserService userService;

    @Test
    public void createUserTest() {
        when(userService.createUser(any())).thenReturn(new User());

        ResponseEntity<User> responseEntity = cut.createUser(any());

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void deleteUserTest() throws NotFoundException {
        doNothing().when(userService).deleteUser(any());

        ResponseEntity<Void> responseEntity = cut.deleteUser(any());

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void updateUserTest() throws NotFoundException {
        when(userService.updateUser(any(), any())).thenReturn(new User());

        ResponseEntity<User> responseEntity = cut.updateUser(any(), any());

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void updateRoleTest() throws NotFoundException {
        when(userService.updateUserRole(any(), any())).thenReturn(new User());

        ResponseEntity<User> responseEntity = cut.updateRole(any(), any());

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void listUsersTest() {
        List<User> userList = List.of(new User(), new User());
        when(userService.findAllUsers()).thenReturn(userList);

        ResponseEntity<List<User>> responseEntity = cut.listUsers();

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertEquals(2, responseEntity.getBody().size());
    }
}
