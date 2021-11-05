package gr.sgdigital.movies.repository;

import gr.sgdigital.common.repository.BaseRepository;
import gr.sgdigital.movies.domain.Movie;

public interface MovieRepository extends BaseRepository<Movie, Long> {
	public Movie findByTitle (String title);
}



