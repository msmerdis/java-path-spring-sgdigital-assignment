package gr.sgdigital.movies.repository;

import gr.sgdigital.common.repository.BaseRepository;
import gr.sgdigital.movies.domain.Episode;
import gr.sgdigital.movies.transfer.EpisodeCreateDTO;
import gr.sgdigital.movies.transfer.EpisodeDetailViewDTO;
import gr.sgdigital.movies.transfer.EpisodeSimpleViewDTO;
import gr.sgdigital.movies.transfer.EpisodeUpdateDTO;

public interface EpisodeRepository extends BaseRepository<Long, Episode, EpisodeCreateDTO, EpisodeUpdateDTO, EpisodeSimpleViewDTO, EpisodeDetailViewDTO> {
}



