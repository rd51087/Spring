package com.dariaromanowska.lab09;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PersonService personService;

    public PeopleController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public String listPeople(Model model) {
        model.addAttribute("people", personService.getPeople());
        return "people";
    }

    @GetMapping("/{id}")
    public String showPerson(@PathVariable int id, Model model, RedirectAttributes redirectAttributes) {
        try {
            model.addAttribute("person", personService.getPerson(id));
            model.addAttribute("personId", id);
            return "person";
        } catch (IndexOutOfBoundsException ex) {
            redirectAttributes.addFlashAttribute("error", "Person not found.");
            return "redirect:/people";
        }
    }

    @GetMapping("/new")
    public String newPersonForm(Model model) {
        model.addAttribute("person", new Person());
        return "person-form";
    }

    @GetMapping("/{id}/edit")
    public String editPersonForm(@PathVariable int id, Model model, RedirectAttributes redirectAttributes) {
        try {
            model.addAttribute("person", personService.getPerson(id));
            model.addAttribute("personId", id);
            return "person-edit";
        } catch (IndexOutOfBoundsException ex) {
            redirectAttributes.addFlashAttribute("error", "Person not found.");
            return "redirect:/people";
        }
    }

    @PostMapping
    public String addPerson(@ModelAttribute Person person, RedirectAttributes redirectAttributes) {
        personService.addPerson(person);
        redirectAttributes.addFlashAttribute("success", "Person added.");
        return "redirect:/people";
    }

    @PostMapping("/{id}")
    public String updatePerson(@PathVariable int id, @ModelAttribute Person person,
                               RedirectAttributes redirectAttributes) {
        try {
            personService.setPerson(id, person);
            redirectAttributes.addFlashAttribute("success", "Person updated.");
            return "redirect:/people/" + id;
        } catch (IndexOutOfBoundsException ex) {
            redirectAttributes.addFlashAttribute("error", "Person not found.");
            return "redirect:/people";
        }
    }

    @PostMapping("/{id}/delete")
    public String deletePerson(@PathVariable int id, RedirectAttributes redirectAttributes) {
        try {
            personService.removePerson(id);
            redirectAttributes.addFlashAttribute("success", "Person removed.");
        } catch (IndexOutOfBoundsException ex) {
            redirectAttributes.addFlashAttribute("error", "Person not found.");
        }
        return "redirect:/people";
    }
}
