package gr.sgdigital.movies.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.sgdigital.common.service.BaseServiceImpl;
import gr.sgdigital.movies.domain.Serie;
import gr.sgdigital.movies.repository.SerieRepository;
import gr.sgdigital.movies.transfer.SerieCreateDTO;
import gr.sgdigital.movies.transfer.SerieDetailViewDTO;
import gr.sgdigital.movies.transfer.SerieSimpleViewDTO;
import gr.sgdigital.movies.transfer.SerieUpdateDTO;

@Service
public class SerieServiceImpl extends BaseServiceImpl<
	Long,
	Serie,
	SerieCreateDTO,
	SerieUpdateDTO,
	SerieSimpleViewDTO,
	SerieDetailViewDTO,
	SerieRepository
> implements SerieService {

	@Autowired
	public SerieServiceImpl(SerieRepository repository) {
		super(repository, Serie.class);
	}

}



