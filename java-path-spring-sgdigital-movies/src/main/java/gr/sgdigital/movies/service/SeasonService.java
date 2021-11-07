package gr.sgdigital.movies.service;

import gr.sgdigital.common.service.BaseService;
import gr.sgdigital.movies.domain.Season;
import gr.sgdigital.movies.transfer.SeasonCreateDTO;
import gr.sgdigital.movies.transfer.SeasonDetailViewDTO;
import gr.sgdigital.movies.transfer.SeasonSimpleViewDTO;
import gr.sgdigital.movies.transfer.SeasonUpdateDTO;

public interface SeasonService extends BaseService<Long, Season, SeasonCreateDTO, SeasonUpdateDTO, SeasonSimpleViewDTO, SeasonDetailViewDTO> {
}



