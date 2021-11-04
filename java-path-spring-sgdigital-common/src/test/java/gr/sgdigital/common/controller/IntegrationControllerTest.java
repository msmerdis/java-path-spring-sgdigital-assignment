package gr.sgdigital.common.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import gr.sgdigital.common.base.BaseTestController;
import gr.sgdigital.common.transfer.ApiStatus;

/**
 * This class represents a test controller
 */
@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationControllerTest extends BaseTestController {
	@Autowired
	private MockMvc mockMvc;

	// Successful request

	@Test
	public void test () throws Exception {
		mockMvc.perform(get("/api/integration"))
			.andExpect(status().isOk())
			.andExpect(content().json(generateSuccessJson("OK")));
	}

	// Testing api errors

	@Test
	public void accepted() throws Exception {
		mockMvc.perform(get("/api/integration/accepted"))
			.andExpect(status().isAccepted())
			.andExpect(content().json(generateAcceptedJson(null)));
	}

	@Test
	public void badRequest() throws Exception {
		mockMvc.perform(get("/api/integration/badrequest"))
			.andExpect(status().isBadRequest())
			.andExpect(content().json(generateBadRequestJson(null, null)));
	}

	@Test
	public void conflict() throws Exception {
		mockMvc.perform(get("/api/integration/conflict"))
			.andExpect(status().isConflict())
			.andExpect(content().json(generateConflictJson(null, null)));
	}

	@Test
	public void created() throws Exception {
		mockMvc.perform(get("/api/integration/created"))
			.andExpect(status().isCreated())
			.andExpect(content().json(generateCreatedJson(null)));
	}

	@Test
	public void forbidden() throws Exception {
		mockMvc.perform(get("/api/integration/forbidden"))
			.andExpect(status().isForbidden())
			.andExpect(content().json(generateForbiddenJson(null, null)));
	}

	@Test
	public void methodNotAllowed() throws Exception {
		mockMvc.perform(get("/api/integration/methodnotallowed"))
			.andExpect(status().isMethodNotAllowed())
			.andExpect(content().json(generateMethodNotAllowedJson(null, null)));
	}

	@Test
	public void noContent() throws Exception {
		mockMvc.perform(get("/api/integration/nocontent"))
			.andExpect(status().isNoContent())
			.andExpect(content().json(generateNoContentJson(null)));
	}

	@Test
	public void notFound() throws Exception {
		mockMvc.perform(get("/api/integration/notfound"))
			.andExpect(status().isNotFound())
			.andExpect(content().json(generateNotFoundJson(null, null)));
	}

	@Test
	public void requestTimeout() throws Exception {
		mockMvc.perform(get("/api/integration/requesttimeout"))
			.andExpect(status().isRequestTimeout())
			.andExpect(content().json(generateRequestTimeoutJson(null, null)));
	}

	@Test
	public void success() throws Exception {
		mockMvc.perform(get("/api/integration/success"))
			.andExpect(status().isOk())
			.andExpect(content().json(generateSuccessJson(null)));
	}

	@Test
	public void internalServerError() throws Exception {
		mockMvc.perform(get("/api/integration/internalservererror"))
			.andExpect(status().isInternalServerError())
			.andExpect(content().json(generateJson(new ApiStatus(HttpStatus.INTERNAL_SERVER_ERROR, null), null)));
	}
}



