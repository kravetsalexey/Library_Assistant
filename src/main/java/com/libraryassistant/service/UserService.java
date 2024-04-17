package com.libraryassistant.service;

import com.libraryassistant.entity.User;
import com.libraryassistant.exceptions.EntityAlreadyExistsException;
import com.libraryassistant.exceptions.InvalidEmailFormatException;
import com.libraryassistant.exceptions.UserNotFoundException;
import com.libraryassistant.repository.BookLoanRepository;
import com.libraryassistant.repository.UserRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookLoanRepository bookLoanRepository;

    @Autowired
    EntityManager em;

    public User addUser(User user) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(user.getEmail());
        if (!matcher.matches()) {
            throw new InvalidEmailFormatException();
        }
        for (User allUser : getAllUsers()) {
            if (user.getName().equals(allUser.getName()) && user.getEmail().equals(allUser.getEmail())){
                throw new EntityAlreadyExistsException();
            }
        }
        return userRepository.save(user);
    }

    @Transactional
    public User getUser(Long id) {
        return em.find(User.class, id);
    }

    public User getUserWithMostBooksRead(LocalDate startDate, LocalDate endDate) {
        Long userId = bookLoanRepository.findUserWithMostBooksRead(startDate, endDate);
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    @Transactional
    public User updateUser(User user){
        if (!em.contains(user)){
            user = em.merge(user);
        }
        return user;
    }

    @Transactional
    public User deleteUser(Long id){
        User user = getUser(id);
        em.remove(user);
        return user;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
