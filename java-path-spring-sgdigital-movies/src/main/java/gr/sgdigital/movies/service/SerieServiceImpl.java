package gr.sgdigital.movies.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.sgdigital.common.service.BaseServiceImpl;
import gr.sgdigital.movies.domain.Serie;
import gr.sgdigital.movies.repository.SerieRepository;

@Service
public class SerieServiceImpl extends BaseServiceImpl<Serie, Long> implements SerieService {

	public SerieServiceImpl(@Autowired SerieRepository repository) {
		super(repository);
	}

	@Override
	public Serie findByTitle(String title) {
		return ((SerieRepository)repository).findByTitle(title);
	}
}



