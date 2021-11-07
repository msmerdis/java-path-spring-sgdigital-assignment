package gr.sgdigital.movies.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gr.sgdigital.common.controller.BaseController;
import gr.sgdigital.movies.domain.Movie;
import gr.sgdigital.movies.service.MovieService;
import gr.sgdigital.movies.transfer.MovieCreateDTO;
import gr.sgdigital.movies.transfer.MovieDetailViewDTO;
import gr.sgdigital.movies.transfer.MovieSimpleViewDTO;
import gr.sgdigital.movies.transfer.MovieUpdateDTO;

@RestController
@RequestMapping("/api/movie")
public class MovieController extends BaseController<
	Long,
	Movie,
	MovieCreateDTO,
	MovieUpdateDTO,
	MovieSimpleViewDTO,
	MovieDetailViewDTO,
	MovieService
> {

	@Autowired
	public MovieController(MovieService service) {
		super(service);
	}

}



