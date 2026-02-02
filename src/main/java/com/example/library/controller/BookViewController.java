package com.example.library.controller;

import com.example.library.model.Author;
import com.example.library.model.Book;
import com.example.library.model.Genre;
import com.example.library.service.AuthorService;
import com.example.library.service.BookService;
import com.example.library.service.GenreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books-view")
public class BookViewController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    public BookViewController(BookService bookService, AuthorService authorService, GenreService genreService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "books";
    }

    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("book", new Book());
        return "book-form";
    }

    @PostMapping
    public String saveBook(@RequestParam String title,
                           @RequestParam String isbn,
                           @RequestParam String authorFirstName,
                           @RequestParam String authorLastName,
                           @RequestParam String genreName) {

        Author author = authorService.findByFirstAndLastName(authorFirstName, authorLastName);
        if (author == null) {
            author = new Author();
            author.setFirstName(authorFirstName);
            author.setLastName(authorLastName);
            authorService.save(author);
        }

        Genre genre = genreService.findByName(genreName);
        if (genre == null) {
            genre = new Genre();
            genre.setName(genreName);
            genreService.save(genre);
        }

        Book book = new Book();
        book.setTitle(title);
        book.setIsbn(isbn);
        book.setAuthor(author);
        book.setGenre(genre);

        bookService.save(book);
        return "redirect:/books-view";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        bookService.deleteById(id);
        return "redirect:/books-view";
    }
}
