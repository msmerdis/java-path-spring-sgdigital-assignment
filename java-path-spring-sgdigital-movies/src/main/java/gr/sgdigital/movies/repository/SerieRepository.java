package gr.sgdigital.movies.repository;

import gr.sgdigital.common.repository.BaseRepository;
import gr.sgdigital.movies.domain.Movie;
import gr.sgdigital.movies.domain.Serie;

public interface SerieRepository extends BaseRepository<Serie, Long> {
	public Serie findByTitle (String title);
}



