package gr.sgdigital.movies.service;

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
import gr.sgdigital.movies.domain.Episode;
import gr.sgdigital.movies.repository.EpisodeRepository;
import gr.sgdigital.movies.transfer.EpisodeCreateDTO;
import gr.sgdigital.movies.transfer.EpisodeDetailViewDTO;
import gr.sgdigital.movies.transfer.EpisodeSimpleViewDTO;
import gr.sgdigital.movies.transfer.EpisodeUpdateDTO;

@Service
public class EpisodeServiceImpl extends AbstractServiceImpl<
	Long,
	Episode,
	EpisodeCreateDTO,
	EpisodeUpdateDTO,
	EpisodeSimpleViewDTO,
	EpisodeDetailViewDTO,
	EpisodeRepository
> implements EpisodeService {

	private SeasonService seasonService;

	@Autowired
	public EpisodeServiceImpl(EpisodeRepository repository, EntityManagerFactory entityManagerFactory, SeasonService seasonService) {
		super(repository, entityManagerFactory, Episode.class);

		this.seasonService = seasonService;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
	public EpisodeDetailViewDTO create(EpisodeCreateDTO dto) throws ApiStatus, Exception {
		Episode episode = new Episode ();

		dto.updateEntity(episode);

		// we need to map title with the actual persistent entity
		// for the save operation to be successful
		seasonService.updateSeasonWithSerie(episode, dto.getSeasonId());

		return repository.saveAndFlush(episode).detailView();
	}

	@Override
	protected TermMatchingContext addSearchFields (TermContext context) {
		return context.onFields ("name", "desc");
	}

}



