package com.libraryassistant.controller;

import com.libraryassistant.entity.User;
import com.libraryassistant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User createdUser = userService.addUser(user);
        return ResponseEntity.created(URI.create("/add/" + createdUser.getId())).body(createdUser);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/get")
    public ResponseEntity<User> getUser(@RequestParam Long id) {
        User user = userService.getUser(id);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<User> deleteUser(@RequestParam Long id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }

    @GetMapping("/bestreader")
    public ResponseEntity<User> getUserWithMostBookRead(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        User user = userService.getUserWithMostBooksRead(startDate, endDate);
        return ResponseEntity.ok(user);
    }
}