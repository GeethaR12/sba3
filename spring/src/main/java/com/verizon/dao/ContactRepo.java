package com.verizon.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.verizon.model.Contacts;
@Repository
public interface ContactRepo extends JpaRepository<Contacts, String>{

	boolean existsByMail(String mail);

}
