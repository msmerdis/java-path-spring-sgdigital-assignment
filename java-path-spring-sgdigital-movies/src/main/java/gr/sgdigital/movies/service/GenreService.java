package gr.sgdigital.movies.service;

import gr.sgdigital.common.service.BaseService;
import gr.sgdigital.movies.domain.Genre;

public interface GenreService extends BaseService<Genre, Integer> {
	Genre findByName (String name);
}



