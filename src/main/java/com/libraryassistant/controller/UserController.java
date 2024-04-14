package com.libraryassistant.controller;

import com.libraryassistant.entity.User;
import com.libraryassistant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.addUser(user));
    }
    @PostMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user){
        return ResponseEntity.ok(userService.updateUser(user));
    }

    @GetMapping("/getall")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/get")
    public ResponseEntity<User> getUser(@RequestParam Long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<User> deleteUser(@RequestParam Long id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }

    @GetMapping("/bestreader")
    public ResponseEntity<User> getUserWithMostBookRead(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        return ResponseEntity.ok(userService.getUserWithMostBooksRead(startDate,endDate));
    }
}