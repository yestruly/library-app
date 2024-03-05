package com.group.libraryapp.service.user;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.repository.user.UserRepository;
import com.group.libraryapp.domain.user.User;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceV2 {
  private final UserRepository userRepository;

  public UserServiceV2(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  //아래 있는 함수가 시작될 때 start transaction을 해줌(트랜잭션 시작)
  //함수가 에러 없이 잘 끝났다면 commit
  //문제가 발생했다면 rollback
  @Transactional
  public void saveUser(UserCreateRequest request){
    User user = userRepository.save(new User(request.getName(), request.getAge()));
  }

  @Transactional(readOnly = true)
  public List<UserResponse> getUsers(){
    return userRepository.findAll().stream().map(UserResponse::new)
        .collect(Collectors.toList());

  }

  @Transactional
  public void updateUser(UserUpdateRequest request){
    User user = userRepository.findById(request.getId())
        .orElseThrow(IllegalAccessError::new);

    user.updateName(request.getName());
  }

  @Transactional
  public void deleteUser(String name){
    User user = userRepository.findByName(name)
        .orElseThrow(IllegalAccessError::new);
    userRepository.delete(user);

//    if(!userRepository.existsByName(name)){
//      throw new IllegalArgumentException();
//    }

  }
}
