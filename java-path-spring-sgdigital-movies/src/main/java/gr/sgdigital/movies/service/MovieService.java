package gr.sgdigital.movies.service;

import gr.sgdigital.common.service.AbstractService;
import gr.sgdigital.movies.domain.Movie;
import gr.sgdigital.movies.transfer.MovieCreateDTO;
import gr.sgdigital.movies.transfer.MovieDetailViewDTO;
import gr.sgdigital.movies.transfer.MovieSimpleViewDTO;
import gr.sgdigital.movies.transfer.MovieUpdateDTO;

public interface MovieService extends AbstractService<Long, Movie, MovieCreateDTO, MovieUpdateDTO, MovieSimpleViewDTO, MovieDetailViewDTO> {
}



