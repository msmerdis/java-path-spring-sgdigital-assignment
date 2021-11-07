package gr.sgdigital.movies.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.sgdigital.common.service.BaseServiceImpl;
import gr.sgdigital.movies.domain.Movie;
import gr.sgdigital.movies.repository.MovieRepository;
import gr.sgdigital.movies.transfer.MovieCreateDTO;
import gr.sgdigital.movies.transfer.MovieDetailViewDTO;
import gr.sgdigital.movies.transfer.MovieSimpleViewDTO;
import gr.sgdigital.movies.transfer.MovieUpdateDTO;

@Service
public class MovieServiceImpl extends BaseServiceImpl<
	Long,
	Movie,
	MovieCreateDTO,
	MovieUpdateDTO,
	MovieSimpleViewDTO,
	MovieDetailViewDTO,
	MovieRepository
> implements MovieService {

	@Autowired
	public MovieServiceImpl(MovieRepository repository) {
		super(repository, Movie.class);
	}

}



