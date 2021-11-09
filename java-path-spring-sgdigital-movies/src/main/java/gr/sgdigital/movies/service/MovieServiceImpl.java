package gr.sgdigital.movies.service;

import java.util.Optional;

import javax.persistence.EntityManagerFactory;

import org.hibernate.search.query.dsl.TermContext;
import org.hibernate.search.query.dsl.TermMatchingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import gr.sgdigital.common.service.AbstractServiceImpl;
import gr.sgdigital.common.transfer.ApiStatus;
import gr.sgdigital.common.transfer.status.ConflictException;
import gr.sgdigital.common.transfer.status.NotFoundException;
import gr.sgdigital.movies.domain.Movie;
import gr.sgdigital.movies.repository.MovieRepository;
import gr.sgdigital.movies.transfer.MovieCreateDTO;
import gr.sgdigital.movies.transfer.MovieDetailViewDTO;
import gr.sgdigital.movies.transfer.MovieSimpleViewDTO;
import gr.sgdigital.movies.transfer.MovieUpdateDTO;
import gr.sgdigital.movies.transfer.TitleCreateDTO;
import gr.sgdigital.movies.transfer.TitleUpdateDTO;

@Service
public class MovieServiceImpl extends AbstractServiceImpl<
	Long,
	Movie,
	MovieCreateDTO,
	MovieUpdateDTO,
	MovieSimpleViewDTO,
	MovieDetailViewDTO,
	MovieRepository
> implements MovieService {

	private TitleService titleService;

	@Autowired
	public MovieServiceImpl(MovieRepository repository, EntityManagerFactory entityManagerFactory, TitleService titleService) {
		super(repository, entityManagerFactory, Movie.class);

		this.titleService = titleService;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
	public MovieDetailViewDTO create(MovieCreateDTO dto) throws ApiStatus, Exception {
		Movie movie = new Movie ();

		dto.updateEntity(movie);

		// we need to map title with the actual persistent entity
		// for the save operation to be successful
		titleService.createMovieTitle(movie, movieToTitleCreateDTOConvertion(dto));

		return repository.saveAndFlush(movie).detailView();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
	public void update(MovieUpdateDTO dto) throws ApiStatus {
		Optional<Movie> optionalMovie = repository.findById(dto.getId());

		if (optionalMovie.isEmpty()) {
			throw new NotFoundException("Movie not found.");
		}

		Movie movie = optionalMovie.get();

		dto.updateEntity(movie);

		titleService.updateTitle(movie.getTitle(), movieToTitleUpdateDTOConvertion(dto));

		try {
			repository.saveAndFlush(movie);
		} catch (Exception e) {
			throw new ConflictException("Cannot update entity");
		}

	}

	private TitleCreateDTO movieToTitleCreateDTOConvertion (MovieCreateDTO dto) {
		TitleCreateDTO title = new TitleCreateDTO ();

		title.setTitleName(dto.getMovieName());
		title.setTitleDesc(dto.getMovieDesc());
		title.setGenres(dto.getGenres());

		return title;
	}

	private TitleUpdateDTO movieToTitleUpdateDTOConvertion (MovieUpdateDTO dto) {
		TitleUpdateDTO title = new TitleUpdateDTO ();

		title.setTitleName(dto.getMovieName());
		title.setTitleDesc(dto.getMovieDesc());
		title.setGenres(dto.getGenres());

		return title;
	}

	@Override
	protected TermMatchingContext addSearchFields(TermContext context) {
		// movie is not indexed
		return null;
	}

}



