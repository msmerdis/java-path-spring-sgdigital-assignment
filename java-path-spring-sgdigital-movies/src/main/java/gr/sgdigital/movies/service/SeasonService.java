package gr.sgdigital.movies.service;

import gr.sgdigital.common.service.AbstractService;
import gr.sgdigital.common.transfer.status.NotFoundException;
import gr.sgdigital.movies.domain.Episode;
import gr.sgdigital.movies.domain.Season;
import gr.sgdigital.movies.transfer.SeasonCreateDTO;
import gr.sgdigital.movies.transfer.SeasonDetailViewDTO;
import gr.sgdigital.movies.transfer.SeasonSimpleViewDTO;
import gr.sgdigital.movies.transfer.SeasonUpdateDTO;

public interface SeasonService extends AbstractService<Long, Season, SeasonCreateDTO, SeasonUpdateDTO, SeasonSimpleViewDTO, SeasonDetailViewDTO> {

	void updateSeasonWithSerie(Episode episode, long seasonId) throws NotFoundException;
}



