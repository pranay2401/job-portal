package com.jobPortal.bootstrap;

import com.jobPortal.models.Role;
import com.jobPortal.models.User;
import com.jobPortal.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserLoader implements CommandLineRunner {
    public final UserRepository userRepository;

    public UserLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadUsers();
    }

    private void loadUsers() {
        if (userRepository.count() == 0) {
            userRepository.save(
                    User.builder()
                            .name("Admin1")
                            .email("admin@goodera.com")
                            .mobile("9999999999")
                            .isActive(true)
                            .password("adminpw")
                            .role(Role.ADMIN)
                            .build()
            );
            userRepository.save(
                    User.builder()
                            .name("User1")
                            .email("user1@goodera.com")
                            .mobile("9999999911")
                            .isActive(true)
                            .password("userpw")
                            .role(Role.JOB_SEEKER)
                            .build()
            );
            System.out.println("Sample Users Loaded");
        }
    }
}
