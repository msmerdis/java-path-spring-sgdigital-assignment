package gr.sgdigital.movies.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.sgdigital.common.service.BaseServiceImpl;
import gr.sgdigital.movies.domain.Genre;
import gr.sgdigital.movies.domain.Title;
import gr.sgdigital.movies.repository.GenreRepository;
import gr.sgdigital.movies.transfer.GenreCreateDTO;
import gr.sgdigital.movies.transfer.GenreDetailViewDTO;
import gr.sgdigital.movies.transfer.GenreSimpleViewDTO;
import gr.sgdigital.movies.transfer.GenreUpdateDTO;

@Service
public class GenreServiceImpl extends BaseServiceImpl<
	Integer,
	Genre,
	GenreCreateDTO,
	GenreUpdateDTO,
	GenreSimpleViewDTO,
	GenreDetailViewDTO,
	GenreRepository
> implements GenreService {

	@Autowired
	public GenreServiceImpl(GenreRepository repository) {
		super(repository, Genre.class);
	}

	@Override
	public void mapGenreToTitle(Title title, String name) {
		Genre genre = repository.findByName(name).orElse(null);

		if (genre == null) {
			logger.info("Genre {} not found, creating.", name);
			genre = new Genre();
			genre.setName(name);
			genre = repository.save(genre);
		}

		title.getGenres().add(genre);
	}

	@Override
	public void mapGenreToTitle(Title title, Set<String> names) {
		for (String name: names) {
			mapGenreToTitle(title, name);
		}
	}

}



