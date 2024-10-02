package com.example.petstoremonolithique.Repositories;


import com.example.petstoremonolithique.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query("select s from User s WHERE s.email= ?1")
    Optional<User> findByEmail(String email);
}
