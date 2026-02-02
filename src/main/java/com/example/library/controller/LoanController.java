package com.example.library.controller;

import com.example.library.model.Loan;
import com.example.library.service.LoanService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/loans-view")
public class LoanController {

    private final LoanService service;

    public LoanController(LoanService service) {
        this.service = service;
    }

    @GetMapping
    public String allLoans(Model model) {
        model.addAttribute("loans", service.findAll());
        return "loans"; // → templates/loans.html
    }

    @GetMapping("/my")
    public String myLoans(Model model, Principal principal) {
        String username = principal.getName();
        List<Loan> userLoans = service.findByUsername(username);
        model.addAttribute("loans", userLoans);
        return "my-loans";
    }

    @PostMapping("/borrow")
    public String borrowBook(@RequestParam Long bookId, Principal principal) {
        String username = principal.getName();
        service.borrowBook(bookId, username, LocalDate.now());
        return "redirect:/loans-view/my";
    }

    @PostMapping("/return/{id}")
    public String returnBook(@PathVariable Long id) {
        service.returnLoan(id);
        return "redirect:/loans-view/my";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteById(id);
        return "redirect:/loans-view";
    }
}
