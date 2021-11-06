package gr.sgdigital.movies.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.sgdigital.common.service.BaseServiceImpl;
import gr.sgdigital.movies.domain.Serie;
import gr.sgdigital.movies.domain.Title;
import gr.sgdigital.movies.repository.SerieRepository;

@Service
public class SerieServiceImpl extends BaseServiceImpl<Serie, Long, SerieRepository> implements SerieService {

	@Autowired
	public SerieServiceImpl(SerieRepository repository) {
		super(repository);
	}

	@Override
	public Serie findByTitle(Title title) {
		return ((SerieRepository)repository).findByTitle(title);
	}

	@Override
	public Serie loadOrCreate(Title title, boolean ongoing) {
		Serie serie = repository.findByTitle(title);

		if (serie == null) {
			serie = new Serie ();
			serie.setOngoing(ongoing);
			serie.setTitle(title);
			serie = repository.save(serie);
		}

		return serie;
	}
}



