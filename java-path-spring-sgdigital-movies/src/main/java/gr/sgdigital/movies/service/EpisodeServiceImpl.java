package gr.sgdigital.movies.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.sgdigital.common.service.BaseServiceImpl;
import gr.sgdigital.movies.domain.Episode;
import gr.sgdigital.movies.repository.EpisodeRepository;
import gr.sgdigital.movies.transfer.EpisodeCreateDTO;
import gr.sgdigital.movies.transfer.EpisodeDetailViewDTO;
import gr.sgdigital.movies.transfer.EpisodeSimpleViewDTO;
import gr.sgdigital.movies.transfer.EpisodeUpdateDTO;

@Service
public class EpisodeServiceImpl extends BaseServiceImpl<
	Long,
	Episode,
	EpisodeCreateDTO,
	EpisodeUpdateDTO,
	EpisodeSimpleViewDTO,
	EpisodeDetailViewDTO,
	EpisodeRepository
> implements EpisodeService {

	@Autowired
	public EpisodeServiceImpl(EpisodeRepository repository) {
		super(repository, Episode.class);
	}

}



