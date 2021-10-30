package com.jobPortal.services;

import com.jobPortal.models.User;
import com.jobPortal.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return userRepository.findById(id).get();
        } else {
            return null;
        }
    }

    @Override
    public User insert(User user) {

        double otp = Math.floor(1000 + Math.random() * 9000);
        long otpExpritation = new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(15)).getTime();
        user.setOtp(otp);
        user.setOtpExpirationTime(otpExpritation);
        user.setActive(false);
        return userRepository.save(user);
    }

    @Override
    public void updateUser(Long id, User user) {

        User userFromDb = userRepository.findById(id).get();
        System.out.println(userFromDb.toString());
        userFromDb.setName(user.getName());
        userFromDb.setEmail(user.getEmail());
        userFromDb.setMobile(user.getMobile());
        userFromDb.setPassword(user.getPassword());
        userFromDb.setRole(user.getRole());
        userFromDb.setActive(user.getActive());
        userFromDb.setOtp(user.getOtp());
        userFromDb.setOtpExpirationTime(user.getOtpExpirationTime());

        userRepository.save(userFromDb);

    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public boolean verifyUser(Long userId, Double otp) {
        User userFromDB = getUserById(userId);
        boolean verified = false;
        if (userFromDB != null) {
            verified = new Date().getTime() < userFromDB.getOtpExpirationTime() &&
                    otp.equals(userFromDB.getOtp());

        }
        if (verified) {
            userFromDB.setActive(true);
            updateUser(userId, userFromDB);
        }

        return verified;
    }
}

