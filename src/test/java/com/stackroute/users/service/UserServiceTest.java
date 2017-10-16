package com.stackroute.users.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


import com.stackroute.users.domain.Users;
import com.stackroute.users.repository.Repository;

import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
public class UserServiceTest {
    
       
       private Services userService;
        @Mock
        private Repository repository;
        @Mock
        private Users user;
        @Before
        public void setupMock() {
            MockitoAnnotations.initMocks(this);
            userService=new Services();
            userService.setRepository(repository);
        }
        @Test
        //Test by id
        public void TestgetById() throws Exception {
            // Arrange
            when(repository.findOne(2)).thenReturn(user);
            // Act
            Users retrievedUser = userService.findById(2);
            // Assert
            assertThat(retrievedUser, is(equalTo(user)));
       }
        
        @Test
        public void TestUpdate() throws Exception {
            // Arrange
            when(repository.save(user)).thenReturn(user);
            // Act
            userService.updateUser(user);
            // Assert
            assertThat(repository.findOne(1), is(repository.findOne(1)));
        }
        @Test
        public void TestDelete() throws Exception {
            // Arrange
            doNothing().when(repository).delete(1);
            Repository my = Mockito.mock(Repository.class);
            // Act
           userService.deleteUser(1);
            // Assert
            verify(repository, times(1)).delete(1);
        }
    }