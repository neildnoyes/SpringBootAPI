package com.noyes.SpringAPI;

import com.noyes.SpringAPI.api.model.User;
import com.noyes.SpringAPI.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    UserService userService = new UserService();

    @Test
    void testGetUserById() {

        //when a user service is invoked, we should have 5 users, ids 1-5

        for (int i = 1; i <= 5; i++) {
            Optional<User> user = userService.getUser(i);

            assertTrue(user.isPresent() == true);

            assertEquals(i, user.get().get_id());
        }

    }

}

