package gr.sgdigital.movies.repository;

import gr.sgdigital.common.repository.BaseRepository;
import gr.sgdigital.movies.domain.Serie;
import gr.sgdigital.movies.domain.Title;

public interface SerieRepository extends BaseRepository<Serie, Long> {
	public Serie findByTitle (Title title);
}



