package com.chsu.chsumodilebackend.dao;

import com.chsu.chsumodilebackend.models.Qr;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QrRepository extends CrudRepository<Qr, Integer> {

}
