package gr.sgdigital.movies.service;

import gr.sgdigital.common.service.BaseService;
import gr.sgdigital.movies.domain.Title;
import gr.sgdigital.movies.domain.TitleType;

public interface TitleService extends BaseService<Title, Long> {
	public Title findByTitle  (String title);
	public Title loadOrCreate (String title, TitleType type, String description);
}



