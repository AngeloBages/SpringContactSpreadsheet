package com.contactspreadsheet.controller;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.contactspreadsheet.config.LocalDateEditor;
import com.contactspreadsheet.model.Contact;
import com.contactspreadsheet.service.ContactService;

@Controller
@RequestMapping("/contacts")
public class ContactController {
	
	private final ContactService cs;
	
	public ContactController(ContactService cs) {
		this.cs = cs;
	}

	@GetMapping
	public String home(Model model) {
		model.addAttribute("contacts", cs.getAllContacts());
		return "home";
	}
	
	@GetMapping("/insert")
	public String insertPage() {
		return "contact_insert";
	}
	
	@PostMapping("/insert")
	public String insertAction(@ModelAttribute Contact contact) {
		cs.createContact(contact);
		return "redirect:/contacts";
	}
	
	@GetMapping("/update")
	public String updatePage(@RequestParam("id") Integer id, Model model) {
		model.addAttribute("contact", cs.getContact(id));
		return "contact_update";
	}
	
	@PostMapping("/update")
	public String updateAction(@ModelAttribute Contact contact) {
		cs.updateContact(contact);
		return "redirect:/contacts";
	}
	
	@GetMapping("/delete")
	public String deleteAction(@RequestParam("id") Integer id) {
		cs.removeContact(id);
		return "redirect:/contacts";
	}
	
	@ExceptionHandler(DateTimeParseException.class)
    public String handleDate(Exception ex, RedirectAttributes redirectAttributes) {
		 redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage()); 
		 return "redirect:/contacts";
    }
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public String handleDataIntegrityViolation(DataIntegrityViolationException ex, RedirectAttributes redirectAttributes) {
	    redirectAttributes.addFlashAttribute("errorMessage", "Email already in use!");
	    return "redirect:/contacts";
	}
	
	@InitBinder
	public void bindLocalDate(WebDataBinder binder) {
		binder.registerCustomEditor(LocalDate.class, new LocalDateEditor());
	}
	
	@ExceptionHandler(BindException.class)
	public String handleBindException(BindException ex, RedirectAttributes redirectAttributes) {
		 for (FieldError fieldError : ex.getFieldErrors()) {
		        if ("birthDate".equals(fieldError.getField())) {

		            redirectAttributes.addFlashAttribute("errorMessage", "Invalid Birthdate input!");
		        }
		    }
	    return "redirect:/contacts";
	}
}
