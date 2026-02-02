package com.example.library.controller;

import com.example.library.model.Genre;
import com.example.library.service.GenreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/genres")
public class GenreController {

    private final GenreService service;

    public GenreController(GenreService service){
        this.service = service;
    }

    @GetMapping
    public List<Genre> getAll(){
        return service.findAll();
    }
    @GetMapping("/{id}")
    public Genre getById(@PathVariable Long id){
        return service.findById(id);
    }

    @PostMapping
    public Genre create(@RequestBody Genre genre){
        return service.save(genre);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.deleteById(id);
    }
}
