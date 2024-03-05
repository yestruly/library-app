package com.group.libraryapp.domain.user;

import com.group.libraryapp.domain.user.loanHisotry.UserLoanHistory;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id = null;

  @Column(nullable = false, length = 20)
  private String name;

  @Column
  private Integer age;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<UserLoanHistory> userLoanHistories = new ArrayList<>();

  protected User(){}


  public String getName() {
    return name;
  }

  public Integer getAge() {
    return age;
  }

  public User(String name, Integer age){
    if(name == null || name.isBlank()){
      throw new IllegalArgumentException(String.format("잘못된 name(%s)이 들어왔습니다", name ));
    }

    this.name= name;
    this.age = age;
  }

  public Long getId() {
    return id;
  }

  public void updateName(String name) {
    this.name = name;
  }

  public void loanBook(String bookName){
    this.userLoanHistories.add(new UserLoanHistory(this, bookName));
  }

  public void returnBook(String bookName){
    UserLoanHistory targetHistory = this.userLoanHistories
        .stream()
        .filter(userLoanHistory -> userLoanHistory.getBookName().equals(bookName))
        .findFirst()
        .orElseThrow(IllegalArgumentException::new);

    targetHistory.doReturn();
  }
}
