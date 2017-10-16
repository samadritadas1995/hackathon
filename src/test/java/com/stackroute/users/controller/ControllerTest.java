package com.stackroute.users.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.web.client.TestRestTemplate;

import com.stackroute.users.App;
import com.stackroute.users.domain.Users;

import junit.framework.TestCase;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerTest extends TestCase {
	
	String user1;
	
	@LocalServerPort
	private int port;
	
	TestRestTemplate restTemplate = new TestRestTemplate();
	
	HttpHeaders headers = new HttpHeaders();
	
	Users user;
	
	@Before
    public void setUp() throws Exception {
         user = new Users(1,"chidiya","chidiya@yahoo.com");
    }
	
	private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
	
	@After
    public void tearDown() throws Exception {
    }
    @Test
    public void testAddUsers() throws Exception {
        HttpEntity<Users> entity = new HttpEntity<Users>(user, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/v1.0/userservice/user"),
                HttpMethod.POST, entity, String.class);
        assertNotNull(response);
        String actual = response.getBody();
        System.out.println(actual);
        assertEquals("Done",actual);
    }
    
    @Test
    public void testGetAllUsers() throws Exception {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("v1.0/userservice/user"),
                HttpMethod.GET, entity, String.class);
        assertNotNull(response);
    }
    
    @Test
    public void testgetUsersById() throws Exception{
        HttpEntity<Users> entity = new HttpEntity<Users>(user, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("v1.0/userservice/user/id/1"),
                HttpMethod.GET, entity, String.class);
        assertNotNull(response);
    }
    
    @Test
    public void testdeleteUsersById() throws Exception{
        HttpEntity<Users> entity = new HttpEntity<Users>(user, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("v1.0/userservice/user/id/1"),
                HttpMethod.DELETE, entity, String.class);
        assertNotNull(response);
        String actual = response.getBody();
        System.out.println(actual);
        assertEquals("Deleted",actual);

    }
    
}
