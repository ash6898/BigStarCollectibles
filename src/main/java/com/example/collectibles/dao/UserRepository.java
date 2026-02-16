package com.example.collectibles.dao;

import com.example.collectibles.beans.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Override
    <S extends User> S save(S entity);

}
