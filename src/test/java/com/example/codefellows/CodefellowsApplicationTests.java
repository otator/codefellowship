package com.example.codefellows;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest()
class CodefellowsApplicationTests {
	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@BeforeEach
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
	}

	@Test
	public void givenHomePageURI_whenMockMVC_thenReturnsIndexJSPViewName1() throws Exception {
		this.mockMvc.perform(get("/")).andDo(print())
				.andExpect(view().name("splash.html"));
	}


	@Test
	public void givenHomePageURI_whenMockMVC_thenReturnsIndexJSPViewName2() throws Exception {
		this.mockMvc.perform(get("/signup")).andDo(print())
				.andExpect(view().name("signup.html"));
	}
	@Test
	public void givenHomePageURI_whenMockMVC_thenReturnsIndexJSPViewName3() throws Exception {
		this.mockMvc.perform(get("/login")).andDo(print())
				.andExpect(view().name("login.html"));
	}
}
