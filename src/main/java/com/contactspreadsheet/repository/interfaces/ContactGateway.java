package com.contactspreadsheet.repository.interfaces;

import java.util.List;
import java.util.Optional;

import com.contactspreadsheet.model.Contact;

public interface ContactGateway {

	Contact add(Contact contact);
	Contact update(Contact contact);
	Optional<Contact> findById(Integer id);
	List<Contact> findAll();
	void delete(Integer id);
}
