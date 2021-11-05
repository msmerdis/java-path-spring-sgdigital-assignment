package gr.sgdigital.movies.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.sgdigital.common.service.BaseServiceImpl;
import gr.sgdigital.movies.domain.Movie;
import gr.sgdigital.movies.repository.MovieRepository;

@Service
public class MovieServiceImpl extends BaseServiceImpl<Movie, Long> implements MovieService {

	public MovieServiceImpl(@Autowired MovieRepository repository) {
		super(repository);
	}

	@Override
	public Movie findByTitle(String title) {
		return ((MovieRepository)repository).findByTitle(title);
	}
}



