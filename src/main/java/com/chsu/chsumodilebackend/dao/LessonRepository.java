package com.chsu.chsumodilebackend.dao;

import com.chsu.chsumodilebackend.models.Lesson;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends CrudRepository<Lesson, Integer> {
    List<Lesson> findAllByLogin(String login);
}
