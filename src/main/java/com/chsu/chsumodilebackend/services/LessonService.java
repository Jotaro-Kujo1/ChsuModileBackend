package com.chsu.chsumodilebackend.services;

import com.chsu.chsumodilebackend.dao.LessonRepository;
import com.chsu.chsumodilebackend.models.Lesson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonService {

    @Autowired
    private LessonRepository lessonRepository;

    public List<Lesson> findAllByLogin(String login){
        return lessonRepository.findAllByLogin(login);
    }
}
