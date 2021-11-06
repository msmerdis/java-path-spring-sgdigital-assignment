package gr.sgdigital.movies.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.sgdigital.common.service.BaseServiceImpl;
import gr.sgdigital.movies.domain.Movie;
import gr.sgdigital.movies.domain.Title;
import gr.sgdigital.movies.repository.MovieRepository;

@Service
public class MovieServiceImpl extends BaseServiceImpl<Movie, Long, MovieRepository> implements MovieService {

	@Autowired
	public MovieServiceImpl(MovieRepository repository) {
		super(repository);
	}

	@Override
	public Movie findByTitle(Title title) {
		return repository.findByTitle(title);
	}

	@Override
	public Movie loadOrCreate(Title title, int releaseYear) {
		Movie movie = repository.findByTitle(title);

		if (movie == null) {
			movie = new Movie ();
			movie.setTitle(title);
			movie.setReleasedYear(releaseYear);
			movie = repository.save(movie);
		}

		return movie;
	}
}



