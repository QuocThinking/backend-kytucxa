package com.apache.hotelroom.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.apache.hotelroom.model.User;
import com.apache.hotelroom.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin("*")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public List<User> getAllUser() {
        return userService.getAllUser();
    }

    @GetMapping("/list/{userId}")
    public Optional<User> getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        try {
            User savedUser = userService.createUser(user);
            return ResponseEntity.ok(savedUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUserName(@PathVariable Long id, @RequestBody Map<String, String> request) {
        String tenNhanVien = request.get("tenNhanVien");
        if (tenNhanVien == null) {
            return ResponseEntity.badRequest().body("tenNhanVien cannot be null");
        }

        try {
            User updatedUser = userService.updateUserName(id, tenNhanVien);
            return ResponseEntity.ok(updatedUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update-password/{id}")
    public ResponseEntity<?> updatePassword(@PathVariable Long id, @RequestBody Map<String, String> request) {
        String newPassword = request.get("password");

        if (newPassword == null) {
            return ResponseEntity.badRequest().body("Password cannot be null");
        }

        try {
            User updatedUser = userService.updatePassword(id, newPassword);
            return ResponseEntity.ok(updatedUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUserById(id);
            return ResponseEntity.ok("User deleted successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
