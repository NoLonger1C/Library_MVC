package ru.dfrolov.projects.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.dfrolov.projects.dao.PersonDAO;
import ru.dfrolov.projects.models.Person;
import ru.dfrolov.projects.util.PersonValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private PersonDAO personDAO;
    private PersonValidator personValidator;

    @Autowired
    public PeopleController(PersonDAO personDAO, PersonValidator personValidator) {
        this.personDAO = personDAO;
        this.personValidator = personValidator;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("people",personDAO.index());
        return "people/index";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person")Person person){
        return "people/new";
    }

    @PostMapping()
    public String create(@Valid @ModelAttribute("person")Person person,
                         BindingResult bindingResult){
        personValidator.validate(person,bindingResult);
        if (bindingResult.hasErrors()){
            return "people/new";
        }
        personDAO.create(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}")
    public String showPerson(@PathVariable("id") int id, Model model){
        model.addAttribute("person",personDAO.showPerson(id));
        model.addAttribute("books",personDAO.getBooksOfPerson(id));
        return "people/show";
    }

    @GetMapping("/{id}/edit")
    public String editPerson(@PathVariable("id") int id, Model model){
        model.addAttribute("person",personDAO.showPerson(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String updatePerson(@Valid @ModelAttribute("person")Person person,
                               BindingResult bindingResult,
                               @PathVariable("id") int id){
        personValidator.validate(person,bindingResult);
        if (bindingResult.hasErrors()){
            return "people/edit";
        }
        personDAO.updatePerson(id,person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") int id){
        personDAO.deletePerson(id);
        return "redirect:/people";
    }

}
