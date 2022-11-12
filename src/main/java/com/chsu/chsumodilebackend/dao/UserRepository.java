package com.chsu.chsumodilebackend.dao;

import com.chsu.chsumodilebackend.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CrudRepository<User,Integer> {
    User findByLogin(String login);
}
