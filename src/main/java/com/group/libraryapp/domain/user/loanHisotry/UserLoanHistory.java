package com.group.libraryapp.domain.user.loanHisotry;

import com.group.libraryapp.domain.user.User;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class UserLoanHistory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id = null;

  @ManyToOne()
  private User user;

  @Column(name = "book_name")
  private String bookName;

  @Column(name = "is_return")
  private boolean isReturn;

  public UserLoanHistory(User user, String bookName) {
    this.user = user;
    this.bookName = bookName;
    this.isReturn = false;
  }
  protected UserLoanHistory(){}


  public void doReturn() {
    this.isReturn = true;
  }

  public String getBookName() {
    return this.bookName;
  }
}
