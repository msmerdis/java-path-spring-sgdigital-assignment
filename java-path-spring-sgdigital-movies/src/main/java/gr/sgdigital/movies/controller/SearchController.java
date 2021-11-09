package gr.sgdigital.movies.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import gr.sgdigital.common.transfer.ApiResponse;
import gr.sgdigital.common.transfer.status.NotImplementedException;

@RestController ("/api/search")
public class SearchController {

	@GetMapping("")
	@ResponseStatus(HttpStatus.OK)
	public ApiResponse<List<String>> search(@RequestHeader("X-Search-Terms") String keyword) throws Exception {
		throw new NotImplementedException("Free text search not supported for this entity");
	}
}



