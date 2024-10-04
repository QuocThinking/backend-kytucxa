package com.apache.hotelroom.service.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.apache.hotelroom.model.User;
import com.apache.hotelroom.model.UserRole;
import com.apache.hotelroom.repository.UserRepository;
import com.apache.hotelroom.service.UserService;

@Service
public class UserImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUser() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @Override
    public Optional<User> getUserById(long id) {

        Optional<User> user = userRepository.findById(id);
        return user;

    }

    @Override
    public User createUser(User user) {
        if (userRepository.existsByUserName(user.getUserName())) {
            throw new IllegalArgumentException("Username already exists");
        }
        // Kiểm tra mật khẩu có null hoặc rỗng không
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        if (user.getRules() == null) {
            user.setRules(UserRole.MANAGER); // Hoặc giá trị mặc định mà bạn muốn
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

    @Override
    public User updateUserName(Long id, String tenNhanVien) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
        user.setTenNhanVien(tenNhanVien);
        return userRepository.save(user);
    }

    @Override
    public User updatePassword(Long id, String newPassword) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));

        if (newPassword == null || newPassword.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        return userRepository.save(user);
    }

}
