package gr.sgdigital.movies.repository;

import gr.sgdigital.common.repository.BaseRepository;
import gr.sgdigital.movies.domain.Title;

public interface TitleRepository extends BaseRepository<Title, Long> {
	public Title findByTitle (String title);
}



