package gr.sgdigital.movies.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gr.sgdigital.common.controller.BaseController;
import gr.sgdigital.common.transfer.ApiResponse;
import gr.sgdigital.movies.domain.Genre;
import gr.sgdigital.movies.domain.Movie;
import gr.sgdigital.movies.service.MovieService;

@RestController
@RequestMapping("/api/movie")
public class MovieController extends BaseController<Movie, Long> {

	@Autowired
	public MovieController(MovieService service) {
		super(service);
	}

	@Override
	public ApiResponse<List<Movie>> findAll() {
		ApiResponse<List<Movie>> rtn = super.findAll();

		for (Movie movie : rtn.data) {
			logger.info("| movie {}", movie.getTitle());
			for (Genre genre : movie.getGenres()) {
				logger.info("|  -> {}", genre.getName());
			}
		}
		return rtn;
	}

}



