package gr.sgdigital.movies.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.sgdigital.common.service.BaseServiceImpl;
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

	@Autowired
	public SeasonServiceImpl(SeasonRepository repository) {
		super(repository, Season.class);
	}

}



