package gr.sgdigital.movies.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import gr.sgdigital.common.service.BaseServiceImpl;
import gr.sgdigital.common.transfer.ApiStatus;
import gr.sgdigital.movies.domain.Season;
import gr.sgdigital.movies.repository.SeasonRepository;
import gr.sgdigital.movies.transfer.SeasonCreateDTO;
import gr.sgdigital.movies.transfer.SeasonDetailViewDTO;
import gr.sgdigital.movies.transfer.SeasonSimpleViewDTO;
import gr.sgdigital.movies.transfer.SeasonUpdateDTO;

@Service
public class SeasonServiceImpl extends BaseServiceImpl<
	Long,
	Season,
	SeasonCreateDTO,
	SeasonUpdateDTO,
	SeasonSimpleViewDTO,
	SeasonDetailViewDTO,
	SeasonRepository
> implements SeasonService {

	private SerieService serieService;

	@Autowired
	public SeasonServiceImpl(SeasonRepository repository, SerieService serieService) {
		super(repository, Season.class);

		this.serieService = serieService;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
	public SeasonDetailViewDTO create(SeasonCreateDTO dto) throws ApiStatus, Exception {
		Season season = new Season ();

		dto.updateEntity(season);

		// we need to map title with the actual persistent entity
		// for the save operation to be successful
		serieService.updateSeasonWithSerie(season, dto.getSeriesId());

		return repository.saveAndFlush(season).detailView();
	}

}



