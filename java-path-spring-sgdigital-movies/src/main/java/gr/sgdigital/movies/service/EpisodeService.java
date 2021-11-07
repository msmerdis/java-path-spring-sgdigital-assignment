package gr.sgdigital.movies.service;

import gr.sgdigital.common.service.BaseService;
import gr.sgdigital.movies.domain.Episode;
import gr.sgdigital.movies.transfer.EpisodeCreateDTO;
import gr.sgdigital.movies.transfer.EpisodeDetailViewDTO;
import gr.sgdigital.movies.transfer.EpisodeSimpleViewDTO;
import gr.sgdigital.movies.transfer.EpisodeUpdateDTO;

public interface EpisodeService extends BaseService<Long, Episode, EpisodeCreateDTO, EpisodeUpdateDTO, EpisodeSimpleViewDTO, EpisodeDetailViewDTO> {
}



