package com.example.library.service;

import com.example.library.model.Genre;
import com.example.library.repository.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {
    private final GenreRepository repository;

    public GenreService(GenreRepository repository) {
        this.repository = repository;
    }

    public List<Genre> findAll() {
        return repository.findAll();
    }

    public Genre findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Genre save(Genre genre) {
        return repository.save(genre);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Genre findByName(String name) {
        return repository.findByName(name);
    }
}
