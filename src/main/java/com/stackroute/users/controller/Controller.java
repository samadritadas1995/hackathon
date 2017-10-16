package com.stackroute.users.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.stackroute.users.domain.Users;
import com.stackroute.users.service.Services;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2 
@RestController
@RequestMapping("/v1.0/userservice")
public class Controller {

	@Autowired
	private Services userService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/user")
	public ResponseEntity<?> add(@RequestBody Users user) {
		if(userService.isUserExist(user)) {
			return new ResponseEntity<String>("User Already Exists", HttpStatus.BAD_REQUEST);
		}
		else {
		userService.addUser(user);
		return new ResponseEntity<String>("Done", HttpStatus.OK);}

	}
	
	@RequestMapping("/user")
	public ResponseEntity<?> getAllUsers() {
		List<Users> resultList1 = userService.getAllUsers();

		return new ResponseEntity<List<Users>>(resultList1, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/user/id/{id}", method = RequestMethod.GET)
	public ResponseEntity<Users> getUserById(@PathVariable("id") Integer id) {

		Users restaurant = userService.findById(id);
		if (restaurant == null) {
			return new ResponseEntity<Users>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Users>(restaurant, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/user/id/{id}")
	public ResponseEntity <?> deleteUserById(@PathVariable("id") Integer id) {
		/* Add validation code */
		userService.deleteUser(id);
		return new ResponseEntity<String>("Deleted", HttpStatus.OK);

	}
	

	@RequestMapping(value = "/user/id/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody Users user) {

		Users currentUser = userService.findById(id);

		if (currentUser == null) {

			return new ResponseEntity<String>("user id not found", HttpStatus.NOT_FOUND);
		}
//		currentUser.setUserId(user.getUserId());
		currentUser.setId(user.getId());
		currentUser.setUserName(user.getUserName());
		currentUser.setEmailID(user.getEmailID());
	

		userService.updateUser(currentUser);
		return new ResponseEntity<Users>(currentUser, HttpStatus.OK);
	}


}
