package gr.sgdigital.movies.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.sgdigital.common.service.BaseServiceImpl;
import gr.sgdigital.movies.domain.Season;
import gr.sgdigital.movies.domain.Serie;
import gr.sgdigital.movies.repository.SeasonRepository;

@Service
public class SeasonServiceImpl extends BaseServiceImpl<Season, Long, SeasonRepository> implements SeasonService {

	@Autowired
	public SeasonServiceImpl(SeasonRepository repository) {
		super(repository);
	}

	@Override
	public List<Season> findBySerie(Serie serie) {
		return repository.findBySerie(serie);
	}

	@Override
	public List<Season> findBySerieAndName(Serie serie, String name) {
		return repository.findBySerieAndName(serie, name);
	}

	@Override
	public Season findBySerieAndOrder(Serie serie, int order) {
		return repository.findBySerieAndOrder(serie, order);
	}

	@Override
	public Season loadOrCreate(Serie serie, int order, String name, String description, int releasedYear) {
		Season season = repository.findBySerieAndOrder(serie, order);

		if (season == null) {
			season = new Season ();
			season.setOrder(order);
			season.setName(name);
			season.setDesc(description);
			season.setReleasedYear(releasedYear);
			season.setSerie(serie);
			season = repository.save(season);
		}

		return season;
	}
}



