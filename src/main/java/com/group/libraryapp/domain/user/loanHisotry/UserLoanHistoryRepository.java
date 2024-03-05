package com.group.libraryapp.domain.user.loanHisotry;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLoanHistoryRepository extends JpaRepository<UserLoanHistory, Long> {
  boolean existsByBookNameAndIsReturn(String bookName, boolean isReturn);

}
