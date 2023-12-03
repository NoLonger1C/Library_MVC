package ru.dfrolov.projects.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.dfrolov.projects.dao.BookDAO;
import ru.dfrolov.projects.dao.PersonDAO;
import ru.dfrolov.projects.models.Book;
import ru.dfrolov.projects.models.Person;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BookController {
    private BookDAO bookDAO;
    private PersonDAO personDAO;

    @Autowired
    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping
    public String index(Model model){
        model.addAttribute("books",bookDAO.index());
        return "books/index";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book")Book book){
        return "books/new";
    }

    @PostMapping()
    public String createBook(@Valid @ModelAttribute("book") Book book,
                             BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "books/new";
        }
        bookDAO.createBook(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String showBook(@PathVariable("id") int id, Model model
                            ,@ModelAttribute("person")Person person){
        model.addAttribute("book",bookDAO.showBook(id));
        model.addAttribute("people",personDAO.index());
        model.addAttribute("owner",bookDAO.getOwner(id));
        return "books/show";
    }

    @GetMapping("/{id}/edit")
    public String editBook(@PathVariable("id") int id, Model model){
        model.addAttribute("book", bookDAO.showBook(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String updateBook(@Valid @ModelAttribute("book") Book book,
                             BindingResult bindingResult,
                             @PathVariable("id") int id){

        if (bindingResult.hasErrors()){
            return "books/edit";
        }
        bookDAO.updateBook(id,book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id){
        bookDAO.deleteBook(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/addToPerson")
    public String addToPerson(@PathVariable("id") int id,
                              @ModelAttribute("person")Person person){
        bookDAO.addToPerson(id,person);
        return "redirect:/books/"+id;
    }

    @PatchMapping("{id}/ReleaseOfPerson")
    public String releaseOfPerson(@PathVariable("id") int id) {
        bookDAO.releaseOfPerson(id);
        return "redirect:/books/"+id;
    }
}
