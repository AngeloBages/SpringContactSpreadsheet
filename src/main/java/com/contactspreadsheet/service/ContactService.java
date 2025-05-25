package com.contactspreadsheet.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.contactspreadsheet.model.Contact;

import com.contactspreadsheet.repository.interfaces.ContactGateway;

@Service
public class ContactService {

	private final ContactGateway repository;
	
	 public ContactService(ContactGateway repository) {
		 this.repository = repository;
	 }
	 
	 public List<Contact> getAllContacts() {
		 return repository.findAll();
	 }
	 
	 public Contact getContact(Integer Id) {
		 return repository.findById(Id).get();
	 }
	 
	 public Contact createContact(Contact contact) {
		 return repository.add(contact);
	 }
	 
	 public Contact updateContact(Contact contact) {
         return repository.update(contact);
	 }
	 
	 public void removeContact(Integer id) {
		 repository.delete(id);
	 }
}
