package com.example.demo;

import com.example.demo.config.SecurityUtility;
import com.example.demo.domain.User;
import com.example.demo.domain.security.Role;
import com.example.demo.domain.security.UserRole;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class GroceryAngularApplication implements CommandLineRunner {

    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(GroceryAngularApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        User user1 = new User();
        user1.setFirstName("Diksha");
        user1.setLastName("Pal");
        user1.setUsername("dikshapal");
        user1.setPassword(SecurityUtility.passwordEncoder().encode("Test@1234"));
        user1.setEmail("diksha.suryawansh@gmail.com");
        Set<UserRole> userRoles = new HashSet<>();
        Role role1 = new Role();
        //role1.setRoleId(1);
        role1.setName("ROLE_USER");
        userRoles.add(new UserRole(user1, role1));

        userService.createUser(user1, userRoles);

        userRoles.clear();

        User user2 = new User();
        user2.setFirstName("Admin");
        user2.setLastName("Admin");
        user2.setUsername("admin");
        user2.setPassword(SecurityUtility.passwordEncoder().encode("Test@1234"));
        user2.setEmail("admin@gmail.com");
        Role role2 = new Role();
        //role2.setRoleId(0);
        role2.setName("ROLE_ADMIN");
        userRoles.add(new UserRole(user2, role2));

        userService.createUser(user2, userRoles);

    }
}
