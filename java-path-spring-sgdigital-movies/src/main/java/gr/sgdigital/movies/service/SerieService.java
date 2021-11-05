package gr.sgdigital.movies.service;

import gr.sgdigital.common.service.BaseService;
import gr.sgdigital.movies.domain.Serie;

public interface SerieService extends BaseService<Serie, Long> {
	public Serie findByTitle (String title);
}



