package com.joselara.crmusers.controllers;

import com.joselara.crmusers.models.User;
import com.joselara.crmusers.models.dto.UserDTO;
import com.joselara.crmusers.services.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Secured({"ROLE_ADMIN"})
    @PostMapping(path = "/user", consumes = "application/json")
    public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.createUser(userDTO));
    }

    @Secured({"ROLE_ADMIN"})
    @DeleteMapping(path = "/user/{userId}", consumes = "application/json")
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") final String userId) throws NotFoundException {
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }

    @Secured({"ROLE_ADMIN"})
    @PatchMapping(path = "/user/{userId}", consumes = "application/json")
    public ResponseEntity<User> updateUser(@RequestBody UserDTO userDTO, @PathVariable("userId") final String userId) throws NotFoundException {
        return ResponseEntity.ok(userService.updateUser(userDTO, userId));
    }

    @Secured({"ROLE_ADMIN"})
    @PatchMapping(path = "/user/{userId}/role", consumes = "application/json")
    public ResponseEntity<User> updateRole(@RequestBody UserDTO userDTO, @PathVariable("userId") final String userId) throws NotFoundException {
        return ResponseEntity.ok(userService.updateUserRole(userDTO, userId));
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping(path = "/users", consumes = "application/json")
    public ResponseEntity<List<User>> listUsers() {
        return ResponseEntity.ok(userService.findAllUsers());
    }
}
