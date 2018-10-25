package com.verizon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verizon.dao.ContactRepo;
import com.verizon.model.Contacts;
@Service
public class ContactServiceImpl implements ContactService{
	@Autowired
	private ContactRepo cRepo;

	@Override
	public List<Contacts> getAll() {
		// TODO Auto-generated method stub
		return cRepo.findAll();
	}

	@Override
	public Contacts add(Contacts contact) {
		// TODO Auto-generated method stub
		return cRepo.save(contact);
	}

	@Override
	public boolean existsByMail(String mail) {
		// TODO Auto-generated method stub
		return cRepo.existsByMail(mail);
	}

}
