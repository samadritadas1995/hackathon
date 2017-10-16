package com.stackroute.users.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.stackroute.users.domain.Users;

@Component
public interface Repository extends CrudRepository<Users, Integer> {

}
