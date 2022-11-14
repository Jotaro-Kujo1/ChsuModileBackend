package com.chsu.chsumodilebackend.dao;

import com.chsu.chsumodilebackend.models.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
public interface UserRepository extends CrudRepository<User,Integer> {
        User findByLogin(String login);
        @Transactional
        @Modifying
        @Query(value = "update User u set u.password = :password where u.login = :login")
        void updatePassword(String login,String password);
}
