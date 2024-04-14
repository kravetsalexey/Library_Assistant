package com.libraryassistant.repository;

import com.libraryassistant.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.email = ?3, u.name = ?2 WHERE u.id = ?1")
    void updateUser(Long id, String name, String email);
}
