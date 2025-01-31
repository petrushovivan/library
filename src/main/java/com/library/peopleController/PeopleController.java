package com.library.peopleController;

import com.library.DAO.PersonRepository;
import com.library.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PeopleController {

    PersonRepository personRepository;

    @Autowired
    public PeopleController(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    @GetMapping
    public String index(Model model){
        model.addAttribute("people", personRepository.index());
        return "people/index";
    }

    @PostMapping
    public String save(@ModelAttribute("person") Person person){
        personRepository.save(person);
        return "redirect:/people";
    }

    @GetMapping("/new")
    public String newPerson(Model model){
        model.addAttribute("person", new Person());
        return "people/new";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        Person person = personRepository.show(id);
        model.addAttribute("person", person);
        return "people/show";
    }

    @PostMapping("/{id}")
    public String edit(@PathVariable("id") int id, @ModelAttribute("person") Person person){
        personRepository.update(person, id);
        return "redirect:/people";
    }
}