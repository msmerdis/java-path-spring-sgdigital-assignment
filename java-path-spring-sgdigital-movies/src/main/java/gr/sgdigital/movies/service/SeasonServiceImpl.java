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
import gr.sgdigital.common.transfer.status.NotFoundException;
import gr.sgdigital.movies.domain.Episode;
import gr.sgdigital.movies.domain.Season;
import gr.sgdigital.movies.repository.SeasonRepository;
import gr.sgdigital.movies.transfer.SeasonCreateDTO;
import gr.sgdigital.movies.transfer.SeasonDetailViewDTO;
import gr.sgdigital.movies.transfer.SeasonSimpleViewDTO;
import gr.sgdigital.movies.transfer.SeasonUpdateDTO;

@Service
public class SeasonServiceImpl extends AbstractServiceImpl<
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
	public SeasonServiceImpl(SeasonRepository repository, EntityManagerFactory entityManagerFactory, SerieService serieService) {
		super(repository, entityManagerFactory, Season.class);

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

	@Override
	public void updateSeasonWithSerie(Episode episode, long seasonId) throws NotFoundException {
		Optional<Season> season = repository.findById(seasonId);

		if (season.isEmpty()) {
			throw new NotFoundException("Season not found.");
		}

		episode.setSeason(season.get());
	}

	@Override
	protected TermMatchingContext addSearchFields(TermContext context) {
		return context.onFields("name", "desc");
	}

}



