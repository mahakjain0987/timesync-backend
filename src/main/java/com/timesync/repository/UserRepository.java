package com.timesync.repository;

import com.timesync.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {

    User findByEmail(String email);

}