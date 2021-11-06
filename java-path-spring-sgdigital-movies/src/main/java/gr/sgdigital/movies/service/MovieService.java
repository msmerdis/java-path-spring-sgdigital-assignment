package gr.sgdigital.movies.service;

import gr.sgdigital.common.service.BaseService;
import gr.sgdigital.movies.domain.Movie;
import gr.sgdigital.movies.domain.Title;

public interface MovieService extends BaseService<Movie, Long> {
	public Movie findByTitle  (Title title);
	public Movie loadOrCreate (Title title, int releaseYear);
}



