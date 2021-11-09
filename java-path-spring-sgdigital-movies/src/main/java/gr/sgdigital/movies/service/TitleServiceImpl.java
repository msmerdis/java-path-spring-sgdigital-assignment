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
import gr.sgdigital.movies.base.GenreDiffConsumer;
import gr.sgdigital.movies.domain.Movie;
import gr.sgdigital.movies.domain.Serie;
import gr.sgdigital.movies.domain.Title;
import gr.sgdigital.movies.domain.TitleCrew;
import gr.sgdigital.movies.domain.TitleType;
import gr.sgdigital.movies.repository.TitleRepository;
import gr.sgdigital.movies.transfer.TitleCreateDTO;
import gr.sgdigital.movies.transfer.TitleDetailViewDTO;
import gr.sgdigital.movies.transfer.TitleSimpleViewDTO;
import gr.sgdigital.movies.transfer.TitleUpdateDTO;

@Service
public class TitleServiceImpl extends AbstractServiceImpl<
	Long,
	Title,
	TitleCreateDTO,
	TitleUpdateDTO,
	TitleSimpleViewDTO,
	TitleDetailViewDTO,
	TitleRepository
> implements TitleService {

	private GenreService genreService;

	@Autowired
	public TitleServiceImpl(TitleRepository repository, EntityManagerFactory entityManagerFactory, GenreService genreService) {
		super(repository, entityManagerFactory, Title.class);

		this.genreService = genreService;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
	public TitleDetailViewDTO create(TitleCreateDTO dto) throws ApiStatus, Exception {
		return createTitle (dto, TitleType.UNKNOWN).detailView();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
	public void update(TitleUpdateDTO dto) throws ApiStatus {
		Optional<Title> optionalTitle = repository.findById(dto.getId());

		if (optionalTitle.isEmpty()) {
			throw new NotFoundException("Title not found.");
		}

		updateTitle (optionalTitle.get(), dto);
	}

	@Override
	public void createMovieTitle(Movie movie, TitleCreateDTO dto) {
		movie.setTitle(createTitle (dto, TitleType.MOVIE));
	}

	@Override
	public void createSerieTitle(Serie serie, TitleCreateDTO dto) {
		serie.setTitle(createTitle (dto, TitleType.SERIE));
	}

	private Title createTitle (TitleCreateDTO dto, TitleType type) {
		Title title = new Title ();

		dto.updateEntity(title);
		title.setType(type);

		// we need to map title with the actual persistent entity
		// for the save operation to be successful
		genreService.mapGenreToTitle(title, dto.getGenres());

		return repository.saveAndFlush(title);
	}

	@Override
	public void updateTitle(Title title, TitleUpdateDTO dto) throws ConflictException {
		dto.updateEntity(title);

		GenreDiffConsumer genreDiff = new GenreDiffConsumer(dto.getGenres());

		// identify what genres have changed
		title.getGenres().stream().forEach(genreDiff); 

		// apply the changes to title
		genreDiff.update(title, genreService);

		try {
			repository.saveAndFlush(title);
		} catch (Exception e) {
			throw new ConflictException("Cannot update entity");
		}
	}

	@Override
	public void linkTitleCrew(TitleCrew titleCrew, Long titleId) throws NotFoundException {
		Optional<Title> title = repository.findById(titleId);

		if (title.isEmpty()) {
			throw new NotFoundException("Title not found to link");
		}

		titleCrew.setTitle(title.get());
	}

	@Override
	protected TermMatchingContext addSearchFields(TermContext context) {
		return context.onFields("title", "description");
	}

}



