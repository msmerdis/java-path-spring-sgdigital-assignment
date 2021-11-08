package gr.sgdigital.movies.repository;

import gr.sgdigital.common.repository.AbstractRepository;
import gr.sgdigital.movies.domain.Movie;
import gr.sgdigital.movies.transfer.MovieCreateDTO;
import gr.sgdigital.movies.transfer.MovieDetailViewDTO;
import gr.sgdigital.movies.transfer.MovieSimpleViewDTO;
import gr.sgdigital.movies.transfer.MovieUpdateDTO;

public interface MovieRepository extends AbstractRepository<Long, Movie, MovieCreateDTO, MovieUpdateDTO, MovieSimpleViewDTO, MovieDetailViewDTO> {
}



