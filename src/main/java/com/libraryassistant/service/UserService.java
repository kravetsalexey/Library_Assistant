package com.libraryassistant.service;

import com.libraryassistant.entity.User;
import com.libraryassistant.exceptions.UserNotFoundException;
import com.libraryassistant.repository.BookLoanRepository;
import com.libraryassistant.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookLoanRepository bookLoanRepository;

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }
    public User getUserWithMostBooksRead(LocalDate startDate, LocalDate endDate) {
        Long userId = bookLoanRepository.findUserWithMostBooksRead(startDate, endDate);
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    public User deleteUser(@PathVariable Long id){
        User user = getUser(id);
        userRepository.delete(user);
        return user;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
