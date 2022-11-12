package com.chsu.chsumodilebackend.services;

import com.chsu.chsumodilebackend.dao.UserRepository;
import com.chsu.chsumodilebackend.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(){
        return (List<User>) userRepository.findAll();
    }

    public User findByLogin(String login){
        return userRepository.findByLogin(login);
    }

    public boolean checkUserValid(String login, String password){
        return findAll()
                .stream()
                .anyMatch(s -> s.getLogin().equals(login) && s.getPassword().equals(password));
    }

    public boolean checkUserExist(User user){
        boolean check = findAll()
                .stream()
                .anyMatch(s -> s.getLogin().equals(user.getLogin()) && s.getPassword().equals(user.getPassword()));
        return !check;
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }


}
