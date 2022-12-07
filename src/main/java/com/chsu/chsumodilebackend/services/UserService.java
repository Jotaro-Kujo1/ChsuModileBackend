package com.chsu.chsumodilebackend.services;

import com.chsu.chsumodilebackend.dao.UserRepository;
import com.chsu.chsumodilebackend.models.Lesson;
import com.chsu.chsumodilebackend.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LessonService lessonService;

    @Autowired
    private QrCodeGenerator qrCodeGenerator;

    public List<User> findAll(){
        return (List<User>) userRepository.findAll();
    }

    public User findByLogin(String login){
        User user = userRepository.findByLogin(login);
        if(user.getJobTitle().equals("teacher")){
            user.setLessons(lessonService.findAllByLogin(login));
            qrCodeGenerator.getQr(user);
        }
        return user;
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

    public boolean changePassword(String login,String password){
        if(findByLogin(login) != null){
            userRepository.updatePassword(login,password);
            return true;
        }else return false;
    }

}
