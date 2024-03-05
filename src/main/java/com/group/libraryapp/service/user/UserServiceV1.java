package com.group.libraryapp.service.user;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.repository.user.UserJdbcRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class UserServiceV1 {
  private final UserJdbcRepository userJDBCRepository;

  public UserServiceV1(UserJdbcRepository userJDBCRepository){
    this.userJDBCRepository = userJDBCRepository;
  }
  public void saveUser(UserCreateRequest request){
    userJDBCRepository.saveUser(request.getName(), request.getAge());
  }

  public List<UserResponse> getUsers(){
    return userJDBCRepository.getUsers();
  }

  public void updateUser(UserUpdateRequest request){
    if(userJDBCRepository.isUserNotExist(request.getId())){
      throw new IllegalArgumentException();
    }

    userJDBCRepository.updateUserName(request.getName(), request.getId() );
  }

  public void deleteUser(String name){
    if(userJDBCRepository.isUserNotExist(name)){
      throw new IllegalArgumentException();
    }
    userJDBCRepository.deleteUser(name);
  }

}
