package com.joselara.crmusers.repositories;

import com.joselara.crmusers.models.User;
import com.joselara.crmusers.models.enums.UserRole;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryIntegrationTest {

    @Autowired
    private UserRepository cut;

    @Test
    public void testSaveAndDeleteUser() {
        User user = new User();
        user.setEmail("test@domain.com");
        user.setPassword("testPassword");
        user.setUserRole(UserRole.ROLE_USER);

        cut.save(user);

        User retrievedUser = cut.findById("test@domain.com").get();
        assertEquals(user, retrievedUser);

        cut.delete(user);

        assertEquals(2, cut.findAll().size());
    }
}
