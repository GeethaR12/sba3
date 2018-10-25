package com.verizon.service;

import java.util.List;

import com.verizon.model.Contacts;

public interface ContactService {
	public  List<Contacts> getAll();
	public Contacts add(Contacts contact);
	boolean existsByMail(String mail);


}
