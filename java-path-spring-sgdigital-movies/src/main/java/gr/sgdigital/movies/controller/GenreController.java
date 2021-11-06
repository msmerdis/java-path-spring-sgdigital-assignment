package gr.sgdigital.movies.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gr.sgdigital.common.controller.BaseController;
import gr.sgdigital.movies.domain.Genre;
import gr.sgdigital.movies.service.GenreService;

@RestController
@RequestMapping("/api/genre")
public class GenreController extends BaseController<Genre, Integer, GenreService> {

	@Autowired
	public GenreController(GenreService service) {
		super(service);
	}

}



