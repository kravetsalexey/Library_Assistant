package com.libraryassistant.service;

import com.libraryassistant.entity.User;
import com.libraryassistant.exceptions.EntityAlreadyExistsException;
import com.libraryassistant.exceptions.UserNotFoundException;
import com.libraryassistant.repository.BookLoanRepository;
import com.libraryassistant.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookLoanRepository bookLoanRepository;

    public User addUser(User user) {
        for (User allUser : getAllUsers()) {
            if (user.getName().equals(allUser.getName()) && user.getEmail().equals(allUser.getEmail())){
                throw new EntityAlreadyExistsException();
            }
        }
        return userRepository.save(user);
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public User getUserWithMostBooksRead(LocalDate startDate, LocalDate endDate) {
        Long userId = bookLoanRepository.findUserWithMostBooksRead(startDate, endDate);
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }
    public User updateUser(User user){
        if (userRepository.findById(user.getId()).isEmpty()) {
            throw new UserNotFoundException();
        }
        userRepository.updateUser(user.getId(), user.getName(), user.getEmail());
        return user;
    }
    public User deleteUser(Long id){
        User user = getUser(id);
        userRepository.delete(user);
        return user;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
