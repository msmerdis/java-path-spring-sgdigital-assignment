package gr.sgdigital.movies.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.sgdigital.common.service.BaseServiceImpl;
import gr.sgdigital.movies.domain.Season;
import gr.sgdigital.movies.repository.SeasonRepository;

@Service
public class SeasonServiceImpl extends BaseServiceImpl<Season, Long> implements SeasonService {

	public SeasonServiceImpl(@Autowired SeasonRepository repository) {
		super(repository);
	}
}



