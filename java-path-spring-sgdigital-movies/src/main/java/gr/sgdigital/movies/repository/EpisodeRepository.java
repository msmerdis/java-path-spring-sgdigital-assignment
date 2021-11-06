package gr.sgdigital.movies.repository;

import java.util.List;

import gr.sgdigital.common.repository.BaseRepository;
import gr.sgdigital.movies.domain.Episode;
import gr.sgdigital.movies.domain.Season;

public interface EpisodeRepository extends BaseRepository<Episode, Long> {
	public List<Episode> findBySeason         (Season season);
	public Episode       findBySeasonAndOrder (Season season, int order);
	public List<Episode> findBySeasonAndName  (Season season, String name);
}



