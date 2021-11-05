package gr.sgdigital.movies.repository;

import gr.sgdigital.common.repository.BaseRepository;
import gr.sgdigital.movies.domain.Episode;

public interface EpisodeRepository extends BaseRepository<Episode, Long> {
	public Episode findByName (String name);
}



