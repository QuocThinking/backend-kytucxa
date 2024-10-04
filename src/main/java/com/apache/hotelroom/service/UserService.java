package com.apache.hotelroom.service;

import com.apache.hotelroom.DTO.UserDTO;
import com.apache.hotelroom.model.User;

import java.util.List;

import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    List<User> getAllUser();

    Optional<User> getUserById(long id);

    User createUser(User user);

    User updateUserName(Long id, String tenNhanVien);

    User updatePassword(Long id, String newPassword);

    void deleteUserById(long id);
}
