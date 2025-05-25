package com.contactspreadsheet.repository.interfaces;


import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.contactspreadsheet.model.Contact;

public interface ContactJpaRepository extends JpaRepository<Contact, Integer>{

	@Modifying
    @Transactional
    @Query("UPDATE Contact c SET c.name = :name, c.email = :email, c.birthDate = :birthDate WHERE c.id = :id")
    void updateContactById(@Param("id") Integer id, 
    		@Param("name") String name, 
    		@Param("email") String email, 
    		@Param("birthDate") LocalDate birthDate);
}
