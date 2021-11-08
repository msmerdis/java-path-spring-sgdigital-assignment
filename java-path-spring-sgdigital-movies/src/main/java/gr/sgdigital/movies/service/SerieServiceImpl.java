package gr.sgdigital.movies.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import gr.sgdigital.common.service.BaseServiceImpl;
import gr.sgdigital.common.transfer.ApiStatus;
import gr.sgdigital.common.transfer.status.ConflictException;
import gr.sgdigital.common.transfer.status.NotFoundException;
import gr.sgdigital.movies.domain.Season;
import gr.sgdigital.movies.domain.Serie;
import gr.sgdigital.movies.domain.Serie;
import gr.sgdigital.movies.repository.SerieRepository;
import gr.sgdigital.movies.transfer.SerieCreateDTO;
import gr.sgdigital.movies.transfer.SerieDetailViewDTO;
import gr.sgdigital.movies.transfer.SerieUpdateDTO;
import gr.sgdigital.movies.transfer.SerieCreateDTO;
import gr.sgdigital.movies.transfer.SerieDetailViewDTO;
import gr.sgdigital.movies.transfer.SerieSimpleViewDTO;
import gr.sgdigital.movies.transfer.SerieUpdateDTO;
import gr.sgdigital.movies.transfer.TitleCreateDTO;
import gr.sgdigital.movies.transfer.TitleUpdateDTO;

@Service
public class SerieServiceImpl extends BaseServiceImpl<
	Long,
	Serie,
	SerieCreateDTO,
	SerieUpdateDTO,
	SerieSimpleViewDTO,
	SerieDetailViewDTO,
	SerieRepository
> implements SerieService {

	private TitleService titleService;

	@Autowired
	public SerieServiceImpl(SerieRepository repository, TitleService titleService) {
		super(repository, Serie.class);

		this.titleService = titleService;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
	public SerieDetailViewDTO create(SerieCreateDTO dto) throws ApiStatus, Exception {
		Serie serie = new Serie ();

		dto.updateEntity(serie);

		// we need to map title with the actual persistent entity
		// for the save operation to be successful
		titleService.createSerieTitle(serie, serieToTitleCreateDTOConvertion(dto));

		return repository.saveAndFlush(serie).detailView();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
	public void update(SerieUpdateDTO dto) throws ApiStatus {
		Optional<Serie> optionalSerie = repository.findById(dto.getId());

		if (optionalSerie.isEmpty()) {
			throw new NotFoundException("Serie not found.");
		}

		Serie serie = optionalSerie.get();

		dto.updateEntity(serie);

		titleService.updateTitle(serie.getTitle(), serieToTitleUpdateDTOConvertion(dto));

		try {
			repository.saveAndFlush(serie);
		} catch (Exception e) {
			throw new ConflictException("Cannot update entity");
		}

	}

	private TitleCreateDTO serieToTitleCreateDTOConvertion (SerieCreateDTO dto) {
		TitleCreateDTO title = new TitleCreateDTO ();

		title.setTitleName(dto.getSerieName());
		title.setTitleDesc(dto.getSerieDesc());
		title.setGenres(dto.getGenres());

		return title;
	}

	private TitleUpdateDTO serieToTitleUpdateDTOConvertion (SerieUpdateDTO dto) {
		TitleUpdateDTO title = new TitleUpdateDTO ();

		title.setTitleName(dto.getSerieName());
		title.setTitleDesc(dto.getSerieDesc());
		title.setGenres(dto.getGenres());

		return title;
	}

	@Override
	public void updateSeasonWithSerie(Season season, long seriesId) throws NotFoundException {
		Optional<Serie> serie = repository.findById(seriesId);

		if (serie.isEmpty()) {
			throw new NotFoundException("Serie not found.");
		}

		season.setSerie(serie.get());
	}
}



