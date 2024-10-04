package com.apache.hotelroom.controller;

import java.util.*;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
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

import com.apache.hotelroom.DTO.UserDTO;
import com.apache.hotelroom.model.User;
import com.apache.hotelroom.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin("*")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/list")
    public List<UserDTO> getAllUser() {

        List<User> users = userService.getAllUser();
        return modelMapper.map(users, new TypeToken<List<UserDTO>>() {
        }.getType());

    }

    @GetMapping("/list/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long userId) {
        Optional<User> user = userService.getUserById(userId);

        if (user.isPresent()) {
            UserDTO userDTO = modelMapper.map(user.get(), UserDTO.class);
            return ResponseEntity.ok(userDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        try {
            User savedUser = userService.createUser(user);
            UserDTO userDTO = modelMapper.map(savedUser, UserDTO.class);
            return ResponseEntity.ok(userDTO);
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
            UserDTO userDTO = modelMapper.map(updatedUser, UserDTO.class);
            return ResponseEntity.ok(userDTO);
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
            UserDTO userDTO = modelMapper.map(updatedUser, UserDTO.class);
            return ResponseEntity.ok(userDTO);
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
