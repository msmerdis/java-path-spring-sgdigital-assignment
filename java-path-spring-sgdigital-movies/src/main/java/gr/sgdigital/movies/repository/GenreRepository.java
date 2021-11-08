package gr.sgdigital.movies.repository;

import java.util.Optional;

import gr.sgdigital.common.repository.AbstractRepository;
import gr.sgdigital.movies.domain.Genre;
import gr.sgdigital.movies.transfer.GenreCreateDTO;
import gr.sgdigital.movies.transfer.GenreDetailViewDTO;
import gr.sgdigital.movies.transfer.GenreSimpleViewDTO;
import gr.sgdigital.movies.transfer.GenreUpdateDTO;

public interface GenreRepository extends AbstractRepository<Integer, Genre, GenreCreateDTO, GenreUpdateDTO, GenreSimpleViewDTO, GenreDetailViewDTO> {
	Optional<Genre> findByName (String name);
}



