package gr.sgdigital.movies.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.sgdigital.common.service.BaseServiceImpl;
import gr.sgdigital.movies.domain.Episode;
import gr.sgdigital.movies.repository.EpisodeRepository;

@Service
public class EpisodeServiceImpl extends BaseServiceImpl<Episode, Long> implements EpisodeService {

	public EpisodeServiceImpl(@Autowired EpisodeRepository repository) {
		super(repository);
	}
}



