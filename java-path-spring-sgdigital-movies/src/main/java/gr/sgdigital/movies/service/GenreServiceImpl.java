package gr.sgdigital.movies.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.sgdigital.common.service.BaseServiceImpl;
import gr.sgdigital.movies.domain.Genre;
import gr.sgdigital.movies.repository.GenreRepository;

@Service
public class GenreServiceImpl extends BaseServiceImpl<Genre, Integer, GenreRepository> implements GenreService {

	@Autowired
	public GenreServiceImpl(GenreRepository repository) {
		super(repository);
	}

	@Override
	public Genre loadOrCreate(String name) {
		Genre genre = repository.findByName(name);

		if (genre == null) {
			genre = new Genre ();
			genre.setName(name);
			genre = repository.save(genre);
		}

		return genre;
	}
}



