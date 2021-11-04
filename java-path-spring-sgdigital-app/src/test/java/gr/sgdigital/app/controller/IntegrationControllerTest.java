package gr.sgdigital.app.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import gr.sgdigital.common.base.BaseTestController;

/**
 * This class represents a test controller
 */
@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationControllerTest extends BaseTestController {
	@Autowired
	private MockMvc mockMvc;

	// testing invalid handlers

	@Test
	public void noHandlerFound() throws Exception {
		mockMvc.perform(get("/api/integration/thisisnotavalidhandlerandshouldfail"))
			.andExpect(status().isNotFound())
			.andExpect(content().json(generateNotFoundJson(null, null)));
	}

	@Test
	public void test() throws Exception {
		// post instead of get
		mockMvc.perform(post("/api/integration"))
			.andExpect(status().isMethodNotAllowed())
			.andExpect(content().json(generateMethodNotAllowedJson(null, null)));
	}
}



