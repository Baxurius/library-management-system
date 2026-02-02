package com.example.library.controller;

import com.example.library.model.Author;
import com.example.library.service.AuthorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorService service;

    public AuthorController(AuthorService service) {
        this.service = service;
    }

    @GetMapping
    public List<Author> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Author getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public Author create(@RequestBody Author author) {
        return service.save(author);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }
}
