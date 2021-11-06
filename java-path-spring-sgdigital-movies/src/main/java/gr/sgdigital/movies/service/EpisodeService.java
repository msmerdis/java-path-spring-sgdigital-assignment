package gr.sgdigital.movies.service;

import java.util.List;

import gr.sgdigital.common.service.BaseService;
import gr.sgdigital.movies.domain.Episode;
import gr.sgdigital.movies.domain.Season;

public interface EpisodeService extends BaseService<Episode, Long> {
	public List<Episode> findBySeason         (Season season);
	public List<Episode> findBySeasonAndName  (Season season, String name);
	public Episode       findBySeasonAndOrder (Season season, int order);
	public Episode       loadOrCreate         (Season season, int order, String name, String description, int duration);
}



