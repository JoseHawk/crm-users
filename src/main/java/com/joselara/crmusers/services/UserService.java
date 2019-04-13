package com.joselara.crmusers.services;

import com.joselara.crmusers.converters.UserConverter;
import com.joselara.crmusers.models.User;
import com.joselara.crmusers.models.dto.UserDTO;
import com.joselara.crmusers.repositories.UserRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    public User createUser(UserDTO userInformation) {
        User user = userConverter.map(userInformation, User.class);

        userRepository.save(user);

        return user;
    }

    public void deleteUser(String userEmail) throws NotFoundException {
        User user = findUser(userEmail);

        userRepository.delete(user);
    }

    public User updateUser(UserDTO userInformation, String userEmail) throws NotFoundException {
        User user = findUser(userEmail);
        user.setEmail(userInformation.getEmail());
        user.setSecret(userInformation.getSecret());

        userRepository.save(user);

        return user;
    }

    public User updateUserRole(UserDTO userInformation, String userEmail) throws NotFoundException {
        User user = findUser(userEmail);
        user.setUserRole(userInformation.getUserRole());

        userRepository.save(user);

        return user;
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    private User findUser(String userEmail) throws NotFoundException {
        Optional<User> optionalUser = userRepository.findById(userEmail);

        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new NotFoundException("User not found");
        }
    }
}
