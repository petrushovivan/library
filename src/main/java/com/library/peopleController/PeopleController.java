package com.library.peopleController;

import com.library.DAO.PersonRepository;
import com.library.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
