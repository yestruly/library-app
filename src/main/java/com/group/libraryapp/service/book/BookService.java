package com.group.libraryapp.service.book;

import com.group.libraryapp.domain.book.Book;
import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.loanHisotry.UserLoanHistory;
import com.group.libraryapp.domain.user.loanHisotry.UserLoanHistoryRepository;
import com.group.libraryapp.dto.book.request.BookCreateRequest;
import com.group.libraryapp.dto.book.request.BookLoanRequest;
import com.group.libraryapp.dto.book.request.BookReturnRequest;
import com.group.libraryapp.repository.book.BookRepository;
import com.group.libraryapp.repository.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {

  private final BookRepository bookRepository;
  private final UserRepository userRepository;
  private final UserLoanHistoryRepository userLoanHistoryRepository;

  public BookService(BookRepository bookRepository, UserRepository userRepository,
      UserLoanHistoryRepository userLoanHistoryRepository) {
    this.bookRepository = bookRepository;
    this.userRepository = userRepository;
    this.userLoanHistoryRepository = userLoanHistoryRepository;
  }

  @Transactional
  public void saveBook(BookCreateRequest request) {
    bookRepository.save(new Book(request.getName()));
  }

  @Transactional
  public void loanBook(BookLoanRequest request) {
    Book book = bookRepository.findByName(request.getBookName())
        .orElseThrow(IllegalArgumentException::new);

    if (userLoanHistoryRepository.existsByBookNameAndIsReturn(book.getName(), false)) {
      throw new IllegalArgumentException("대출 중인 책입니다.");
    }

    User user = userRepository.findByName(request.getUserName())
        .orElseThrow(IllegalArgumentException::new);
    user.loanBook(request.getBookName());
  }

  @Transactional
  public void returnBook(BookReturnRequest request) {
    User user = userRepository.findByName(request.getUserName())
        .orElseThrow(IllegalArgumentException::new);

    user.returnBook(request.getBookName());
  }
}
