package com.stackroute.users.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stackroute.users.domain.Users;
import com.stackroute.users.repository.Repository;

@Service
public class Services {

	
@Autowired
	Repository repository;



public Repository getRepository() {
	return repository;
}

public void setRepository(Repository repository) {
	this.repository = repository;
}

public List<Users> getAllUsers( )
{
	List<Users> userList = (List<Users>) repository.findAll();
    return userList;
}

public void addUser(Users user)
{
	repository.save(user);
}
public void deleteUser(int id)
{	
	if(repository.findOne(id)!=null)
		repository.delete(id);	
}

public Users findById(int id) {
	if(repository.findOne(id)==null)
		return null;
	else
		return repository.findOne(id);	
}

public Users findByName(String name) {
    for(Users user : repository.findAll()){
        if(user.getUserName().equalsIgnoreCase(name)){
            return user;
        }
    }
    return null;
}

public void updateUser(Users user) {
	   repository.save(user);
}

public boolean isUserExist(Users user) {
    return findByName(user.getUserName())!=null;
}


}
