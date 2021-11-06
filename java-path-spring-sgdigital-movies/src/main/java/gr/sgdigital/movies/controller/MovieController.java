package gr.sgdigital.movies.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gr.sgdigital.common.controller.BaseController;
import gr.sgdigital.movies.domain.Movie;
import gr.sgdigital.movies.service.MovieService;

@RestController
@RequestMapping("/api/movie")
public class MovieController extends BaseController<Movie, Long, MovieService> {

	@Autowired
	public MovieController(MovieService service) {
		super(service);
	}
}



