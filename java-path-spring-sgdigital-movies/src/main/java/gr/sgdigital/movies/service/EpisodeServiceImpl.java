package gr.sgdigital.movies.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.sgdigital.common.service.BaseServiceImpl;
import gr.sgdigital.movies.domain.Episode;
import gr.sgdigital.movies.domain.Season;
import gr.sgdigital.movies.repository.EpisodeRepository;

@Service
public class EpisodeServiceImpl extends BaseServiceImpl<Episode, Long, EpisodeRepository> implements EpisodeService {

	@Autowired
	public EpisodeServiceImpl(EpisodeRepository repository) {
		super(repository);
	}

	@Override
	public List<Episode> findBySeason(Season season) {
		return repository.findBySeason(season);
	}

	@Override
	public List<Episode> findBySeasonAndName(Season season, String name) {
		return repository.findBySeasonAndName(season, name);
	}

	@Override
	public Episode findBySeasonAndOrder(Season season, int order) {
		return repository.findBySeasonAndOrder(season, order);
	}

	@Override
	public Episode loadOrCreate(Season season, int order, String name, String description, int duration) {
		Episode episode = repository.findBySeasonAndOrder(season, order);

		if (episode == null) {
			episode = new Episode ();
			episode.setOrder(order);
			episode.setName(name);
			episode.setDesc(description);
			episode.setDuration(duration);
			episode.setSeason(season);
			episode = repository.save(episode);
		}

		return episode;
	}
}



