package com.project.flowers.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.flowers.entity.User;
import com.project.flowers.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserContrller {
    @Autowired
    private UserService userService;
    @GetMapping
    public List<User>getAllUsers()
    {
        return this.userService.findAll();
    }
    @PostMapping
    public void save(@RequestBody User user)
    {
        this.userService.save(user);
    }
    
    @DeleteMapping("/{id}")
    public void deletUser(Long id)
    {
        this.userService.deleteUerById(id);
    }
}
