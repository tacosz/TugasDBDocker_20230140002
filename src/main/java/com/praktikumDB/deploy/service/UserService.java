package com.praktikumDB.deploy.service;

import com.praktikumDB.deploy.model.User;
import com.praktikumDB.deploy.repository.UserRepository;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User request){
        request.setId(UUID.randomUUID().toString());
        return  userRepository.save(request);
    }

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public User getUserById(String id){
        return userRepository.findById(id).orElse(null);
    }

    public User updateUser(String id, User request){
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser != null){
            existingUser.setName(request.getName());
            existingUser.setNim(request.getNim());
            return userRepository.save(existingUser);
        }
        return null;
    }
}
