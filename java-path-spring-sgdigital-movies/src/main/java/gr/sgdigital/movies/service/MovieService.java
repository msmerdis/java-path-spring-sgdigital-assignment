package gr.sgdigital.movies.service;

import gr.sgdigital.common.service.BaseService;
import gr.sgdigital.movies.domain.Movie;

public interface MovieService extends BaseService<Movie, Long> {
	public Movie findByTitle (String title);
}



