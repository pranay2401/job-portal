package com.jobPortal.controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jobPortal.models.Role;
import com.jobPortal.models.User;
import com.jobPortal.services.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //The function receives a GET request, processes it and gives back a list of User as a response.
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    //The function receives a GET request, processes it, and gives back a list of User as a response.
    @GetMapping({"/{userId}"})
    public ResponseEntity<User> getUser(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(user, HttpStatus.NO_CONTENT);
        }
    }
    //The function receives a POST request, processes it, creates a new User and saves it to the database, and returns a resource link to the created user.    @PostMapping
    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        User user1 = userService.insert(user);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("user", "/api/v1/user/" + user1.getId().toString());
        return new ResponseEntity<>(user1, httpHeaders, HttpStatus.CREATED);
    }
    //The function receives a PUT request, updates the User with the specified Id and returns the updated User
    @PutMapping({"/{userId}"})
    public ResponseEntity<User> updateUser(@PathVariable("userId") Long userId, @RequestBody User user) {
        userService.updateUser(userId, user);
        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
    }
    //The function receives a DELETE request, deletes the User with the specified Id.
    @DeleteMapping({"/{userId}"})
    public ResponseEntity<User> deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping({"/verify"})
    public ResponseEntity<String> verifyUser(@RequestBody ObjectNode params) {
        Long userId = Long.parseLong(params.get("userId").asText());
        Double otp = Double.parseDouble(params.get("otp").asText());
        boolean verified = userService.verifyUser(userId, otp);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("verify", "/api/v1/user/verify" + userId);

        if (verified) {
            String result = "User successfully verified. Please login";
            return new ResponseEntity<>(result, httpHeaders, HttpStatus.OK);
        } else {
            String result = "UserId and OTP don't match";
            return new ResponseEntity<>(result, httpHeaders, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping({"/login"})
    public ResponseEntity<String> loginUser(@RequestBody ObjectNode params) {
        String email = params.get("email").asText();
        String password = params.get("password").asText();
        String role = params.get("role").asText();

        List<User> users = userService.getUsers();
        Optional<User> userOptional = users.stream().parallel().filter((User user) -> user.getEmail().equals(email)).findFirst();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("login", "/api/v1/user/login" + email);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (!user.getPassword().equals(password)){
                return new ResponseEntity<>("Incorrect password", httpHeaders, HttpStatus.OK);
            }

            if (!user.getRole().name().equals(role)){
                return new ResponseEntity<>("Incorrect role", httpHeaders, HttpStatus.MULTIPLE_CHOICES);
            }
            return new ResponseEntity<>("User successfully logged in.", httpHeaders, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User with email " + email + " doesn't exist", httpHeaders, HttpStatus.BAD_REQUEST);
        }
    }
}
