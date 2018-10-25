package com.verizon;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.verizon.api.ContactApi;
import com.verizon.model.Contacts;
import com.verizon.service.ContactService;
import com.verizon.test.TestUtil;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers=ContactApi.class)
public class TestContact {
	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@MockBean
	private ContactService cServ;

	@Before
	public void setUp() throws Exception {
		mockMvc=MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetAllContacts() throws Exception {
		assertThat(this.cServ).isNotNull();
		List<Contacts> c=new ArrayList<>();
		c.add(new Contacts());
		
		when(this.cServ.getAll()).thenReturn(c);
		this.mockMvc.perform(get("/contact")).andExpect(status().isOk()).andDo(print());
	}

	@Test
	public void testAddContacts() throws  Exception {
		assertThat(this.cServ).isNotNull();
		Contacts c=new Contacts();
		c.setName("Geetha");
		c.setAddress("xyz");
		c.setMobile("7550123903");
		c.setMail("geetha@gmail.com");
		
		when(this.cServ.add(Mockito.any(Contacts.class))).thenReturn(c);
		this.mockMvc.perform(post("/contact").contentType(TestUtil.APPLICATION_JSON_UTF8).content(TestUtil.convertObjectToJsonBytes(c)))
		.andExpect(status().isOk())
		.andDo(print());
	}

}
