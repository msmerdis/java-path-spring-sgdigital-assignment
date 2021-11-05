package gr.sgdigital.movies.repository;

import gr.sgdigital.common.repository.BaseRepository;
import gr.sgdigital.movies.domain.Genre;

public interface GenreRepository extends BaseRepository<Genre, Integer> {
	public Genre findByName (String name);
}



