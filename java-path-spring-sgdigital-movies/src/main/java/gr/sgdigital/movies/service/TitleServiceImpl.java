package gr.sgdigital.movies.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import gr.sgdigital.common.service.BaseServiceImpl;
import gr.sgdigital.common.transfer.ApiStatus;
import gr.sgdigital.movies.domain.Genre;
import gr.sgdigital.movies.domain.Title;
import gr.sgdigital.movies.repository.TitleRepository;
import gr.sgdigital.movies.transfer.TitleCreateDTO;
import gr.sgdigital.movies.transfer.TitleDetailViewDTO;
import gr.sgdigital.movies.transfer.TitleSimpleViewDTO;
import gr.sgdigital.movies.transfer.TitleUpdateDTO;

@Service
public class TitleServiceImpl extends BaseServiceImpl<
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
	public TitleServiceImpl(TitleRepository repository, GenreService genreService) {
		super(repository, Title.class);

		this.genreService = genreService;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
	public TitleDetailViewDTO create(TitleCreateDTO dto) throws ApiStatus, Exception {
		Title title = new Title ();

		dto.updateEntity(title);

		// we need to map title with the actual persistent entity
		// for the save operation to be successful
		genreService.mapGenreToTitle(title, dto.getGenres());

		return repository.save(title).detailView();
	}
}



