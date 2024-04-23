package com.libraryassistant.controller;

import com.libraryassistant.entity.User;
import com.libraryassistant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/users-management")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/managed-users")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.addUser(user));
    }

    @PutMapping("/managed-users/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user){
        return ResponseEntity.ok(userService.updateUser(user));
    }

    @GetMapping("/managed-users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/managed-users/{id}")
    public ResponseEntity<User> getUser(@RequestParam Long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @GetMapping("/managed-users/best-reader")
    public ResponseEntity<User> getUserWithMostBookRead(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        return ResponseEntity.ok(userService.getUserWithMostBooksRead(startDate,endDate));
    }

    @DeleteMapping("/managed-users/{id}")
    public ResponseEntity<?> deleteUser(@RequestParam Long id) {
        return ResponseEntity.ok(" Пользователь " + userService.deleteUser(id) + " удален.");
    }
}