package com.example.petstoremonolithique.Services;





import com.example.petstoremonolithique.Entities.User;

import com.example.petstoremonolithique.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    public UserRepository userRepository;

    public List<User> getUsers() {
        return this.userRepository.findAll();
    }


    public User addUser(User user) {
        Optional<User> optionalUser = this.userRepository.findByEmail(user.getEmail());
        if (optionalUser.isPresent()) {
            throw new IllegalStateException("email is taken, please try a new one");
        } else if (!checkEmail(user.getEmail())) {
            throw new IllegalStateException("email is not corresponding to a valid one");
        } else if (!checkFirstname(user.getFirstname())) {
            throw new IllegalStateException("firstname is too short !!!");
        }
        return this.userRepository.save(user);
    }

    public boolean checkEmail(String email) {
        if ((email.length() >= 12) && email.toLowerCase().contains("@")) {
            return true;
        } else return false;
    }

    public boolean checkFirstname(String firstname) {
        if (firstname.length() > 2) {
            return true;
        } else return false;
    }
    public User updateUser(Long id, User updatedUser) {
        Optional<User> optionalUser = this.userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setFirstname(updatedUser.getFirstname());
            existingUser.setLastname(updatedUser.getLastname());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPasswordHash(updatedUser.getPasswordHash());
            existingUser.setSalt(updatedUser.getSalt());
            existingUser.setPhone(updatedUser.getPhone());
            existingUser.setStatus(updatedUser.getStatus());
            // Update other fields as needed
            return this.userRepository.save(existingUser);
        } else {
            throw new IllegalStateException("User with id " + id + " not found");
        }
    }
    public void deleteUser(Long id) {
        if (this.userRepository.existsById(id)) {
            this.userRepository.deleteById(id);
        } else {
            throw new IllegalStateException("User with id " + id + " not found");
        }
    }
}
