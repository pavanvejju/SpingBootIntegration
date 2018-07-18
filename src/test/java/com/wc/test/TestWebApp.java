/**
 * 
 */
package com.wc.test;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


/**
 * @author pavankumarv
 *
 */
public class TestWebApp extends SpringBootTest1 {

	@Autowired
	public WebApplicationContext webApplicationContext;
	
	private MockMvc mockmvc;
	
	@Before
	public void setup(){
		mockmvc	=	MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void testFetchEmployee() throws Exception{
		mockmvc.perform(get("/employee"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.name").value("emp1"))
				.andExpect(jsonPath("$.empId").value("adfas"));
		
	}
	
	@Test
	public void testAddEmployee() throws Exception{
		mockmvc.perform(post("/employee"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.name").value("emp1"))
				.andExpect(jsonPath("$.empId").value("adfas"));
		
	}
	
	
}
