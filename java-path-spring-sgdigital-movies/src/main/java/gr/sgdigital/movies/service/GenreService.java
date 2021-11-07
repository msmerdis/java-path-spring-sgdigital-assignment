package gr.sgdigital.movies.service;

import java.util.Set;

import gr.sgdigital.common.service.BaseService;
import gr.sgdigital.movies.domain.Genre;
import gr.sgdigital.movies.domain.Title;
import gr.sgdigital.movies.transfer.GenreCreateDTO;
import gr.sgdigital.movies.transfer.GenreDetailViewDTO;
import gr.sgdigital.movies.transfer.GenreSimpleViewDTO;
import gr.sgdigital.movies.transfer.GenreUpdateDTO;

public interface GenreService extends BaseService<Integer, Genre, GenreCreateDTO, GenreUpdateDTO, GenreSimpleViewDTO, GenreDetailViewDTO> {
	public void mapGenreToTitle (Title title, String gerne);
	public void mapGenreToTitle (Title title, Set<String> gernes);
}



