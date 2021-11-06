package gr.sgdigital.movies.service;

import gr.sgdigital.common.service.BaseService;
import gr.sgdigital.movies.domain.Serie;
import gr.sgdigital.movies.domain.Title;

public interface SerieService extends BaseService<Serie, Long> {
	public Serie findByTitle  (Title title);
	public Serie loadOrCreate (Title title, boolean ongoing);
}



