package gr.sgdigital.movies.repository;

import gr.sgdigital.common.repository.AbstractRepository;
import gr.sgdigital.movies.domain.Season;
import gr.sgdigital.movies.transfer.SeasonCreateDTO;
import gr.sgdigital.movies.transfer.SeasonDetailViewDTO;
import gr.sgdigital.movies.transfer.SeasonSimpleViewDTO;
import gr.sgdigital.movies.transfer.SeasonUpdateDTO;

public interface SeasonRepository extends AbstractRepository<Long, Season, SeasonCreateDTO, SeasonUpdateDTO, SeasonSimpleViewDTO, SeasonDetailViewDTO> {
}



