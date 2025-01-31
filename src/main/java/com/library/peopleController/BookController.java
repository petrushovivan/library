package com.library.peopleController;

import com.library.DAO.BookRepository;
import com.library.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/book")
public class BookController {
    BookRepository bookRepository;

    @Autowired
    public BookController(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public String index(Model model){
        model.addAttribute("books", bookRepository.index());
        return "book/index";
    }

    @PostMapping
    public String save(@ModelAttribute("book") Book book){
        bookRepository.save(book);
        return "redirect:/book";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        Book book = bookRepository.show(id);
        model.addAttribute("book", book);
        return "book/show";
    }

    @GetMapping("/new")
    public String newBook(Model model){
        model.addAttribute("book", new Book());
        return "book/new";
    }
}
