package gr.sgdigital.movies.repository;

import gr.sgdigital.common.repository.BaseRepository;
import gr.sgdigital.movies.domain.Movie;
import gr.sgdigital.movies.domain.Title;

public interface MovieRepository extends BaseRepository<Movie, Long> {
	public Movie findByTitle (Title title);
}



