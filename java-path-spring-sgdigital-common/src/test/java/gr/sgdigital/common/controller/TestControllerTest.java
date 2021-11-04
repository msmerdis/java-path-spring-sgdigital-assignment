package gr.sgdigital.common.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


/**
 * This class represents a test controller
 */
@SpringBootTest
@AutoConfigureMockMvc
public class TestControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void test () throws Exception {
		mockMvc.perform(get("/api/test"))
			.andExpect(status().isOk())
			.andExpect(content().string("OK"));
	}
}



