package gr.sgdigital.movies.repository;

import java.util.List;

import gr.sgdigital.common.repository.BaseRepository;
import gr.sgdigital.movies.domain.Season;
import gr.sgdigital.movies.domain.Serie;

public interface SeasonRepository extends BaseRepository<Season, Long> {
	public List<Season> findBySerie         (Serie serie);
	public Season       findBySerieAndOrder (Serie serie, int order);
	public List<Season> findBySerieAndName  (Serie serie, String name);
}



