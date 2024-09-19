package kg.alatoo.library.controllers;


import kg.alatoo.library.entities.BookEntity;
import kg.alatoo.library.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/get-all")
    public List<BookEntity> getAll(){
        return bookRepository.findAll();

    }
    @PostMapping("/create")
    public BookEntity create(@RequestBody BookEntity newBook) {
        return bookRepository.save(newBook);
    }

}
