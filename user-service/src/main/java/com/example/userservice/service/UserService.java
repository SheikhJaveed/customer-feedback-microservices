package com.example.userservice.service;

import com.example.userservice.dto.UserResponse;
import com.example.userservice.entity.User;
import com.example.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public UserResponse createUser(User user) {
        User saved = repo.save(user);
        return toDto(saved);
    }

    public UserResponse getUser(Long id) {
        User u = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
        return toDto(u);
    }

    public List<UserResponse> getAllUsers() {
        return repo.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public UserResponse updateUser(Long id, User update) {
        User u = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
        u.setEmail(update.getEmail() != null ? update.getEmail() : u.getEmail());
        u.setUsername(update.getUsername() != null ? update.getUsername() : u.getUsername());
        // Do not update password here directly — you'd normally use a change-password endpoint.
        User saved = repo.save(u);
        return toDto(saved);
    }

    public void deleteUser(Long id) {
        repo.deleteById(id);
    }

    private UserResponse toDto(User u) {
        return new UserResponse(u.getId(), u.getUsername(), u.getEmail(), u.getRoles());
    }
}
