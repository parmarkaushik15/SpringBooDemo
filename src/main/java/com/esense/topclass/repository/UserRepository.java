package com.esense.topclass.repository;

import org.springframework.data.repository.CrudRepository;

import com.esense.topclass.dbmodel.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
