package com.verizon.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.verizon.model.Contacts;
import com.verizon.service.ContactService;

@RestController
@CrossOrigin
@RequestMapping("/contact")
public class ContactApi {
	@Autowired
	private ContactService cServ;
	
	@GetMapping
	public ResponseEntity<List<Contacts>> getAllContacts(){
		return new ResponseEntity<List<Contacts>>(cServ.getAll(),HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Contacts> addContacts(@RequestBody Contacts contact) {
		ResponseEntity<Contacts> resp = null;

		if (cServ.existsByMail(contact.getMail())) {
			resp = new ResponseEntity<Contacts>(HttpStatus.ALREADY_REPORTED);
		}

		if (resp == null) {
			Contacts c = cServ.add(contact);
			if (c == null)
				resp = new ResponseEntity<Contacts>(HttpStatus.BAD_REQUEST);
			else
				resp = new ResponseEntity<Contacts>(c, HttpStatus.OK);
		}
		return resp;
	}
}
