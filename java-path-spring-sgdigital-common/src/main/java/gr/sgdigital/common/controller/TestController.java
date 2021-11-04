package gr.sgdigital.common.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class represents a test controller
 */
@RestController
@RequestMapping("/api/test")
public class TestController {

	@GetMapping()
	public String test() {
		return "OK";
	}
}



