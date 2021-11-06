package gr.sgdigital.movies.service;

import java.util.List;

import gr.sgdigital.common.service.BaseService;
import gr.sgdigital.movies.domain.Season;
import gr.sgdigital.movies.domain.Serie;

public interface SeasonService extends BaseService<Season, Long> {
	public List<Season> findBySerie         (Serie serie);
	public List<Season> findBySerieAndName  (Serie serie, String name);
	public Season       findBySerieAndOrder (Serie serie, int order);
	public Season       loadOrCreate        (Serie serie, int order, String name, String description, int releasedYear);
}



