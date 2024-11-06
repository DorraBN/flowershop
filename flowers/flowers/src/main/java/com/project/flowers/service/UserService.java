package com.project.flowers.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.flowers.entity.User;
import com.project.flowers.repository.UserRepository;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public List<User>findAll()
    {
        return userRepository.findAll();
    }
    public Optional<User> findUserById(Long id)
    {
        return userRepository.findById(id);
    }
    public void deleteUerById(Long id)
    {
        this.userRepository.deleteById(id);
    }
    public User save(User user)
    {
       return  this.userRepository.save(user);
    }
}
