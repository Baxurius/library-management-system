package com.example.library.service;

import com.example.library.model.Loan;
import com.example.library.model.User;
import com.example.library.model.Book;
import com.example.library.repository.LoanRepository;
import com.example.library.repository.UserRepository;
import com.example.library.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.time.LocalDate;

@Service
public class LoanService {

    private final LoanRepository repository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public LoanService(LoanRepository repository, UserRepository userRepository, BookRepository bookRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    public List<Loan> findAll() {
        return repository.findAll();
    }

    public Loan findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Loan save(Loan loan) {
        return repository.save(loan);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public List<Loan> findByUsername(String username) {
        return repository.findByUser_Username(username);
    }

    public void returnLoan(Long loanId) {
        Loan loan = repository.findById(loanId).orElse(null);
        if (loan != null && loan.getReturnDate() == null) {
            loan.setReturnDate(LocalDate.now());
            repository.save(loan);
        }
    }

    public void borrowBook(Long bookId, String username, LocalDate borrowDate) {
        Book book = bookRepository.findById(bookId).orElse(null);
        User user = userRepository.findByUsername(username).orElse(null);

        if (book != null && user != null) {
            Loan loan = new Loan();
            loan.setBook(book);
            loan.setUser(user);
            loan.setBorrowDate(borrowDate);
            loan.setReturnDate(null);
            repository.save(loan);
        }
    }
}
