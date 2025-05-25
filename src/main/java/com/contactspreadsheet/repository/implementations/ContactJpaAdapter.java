package com.contactspreadsheet.repository.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.contactspreadsheet.model.Contact;
import com.contactspreadsheet.repository.interfaces.ContactGateway;
import com.contactspreadsheet.repository.interfaces.ContactJpaRepository;

@Repository
public class ContactJpaAdapter implements ContactGateway{

	private final ContactJpaRepository repository;
	
	public ContactJpaAdapter(ContactJpaRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public Contact add(Contact contact) {
		repository.save(contact);
		return contact;
	}

	@Override
	public Contact update(Contact contact) {
		repository.updateContactById(contact.getId(), contact.getName(), contact.getEmail(), contact.getBirthDate());
		return contact;
	}

	@Override
	public Optional<Contact> findById(Integer id) {
		return repository.findById(id);
	}

	@Override
	public List<Contact> findAll() {
		return repository.findAll();
	}

	@Override
	public void delete(Integer id) {
		repository.deleteById(id);
	}

}
