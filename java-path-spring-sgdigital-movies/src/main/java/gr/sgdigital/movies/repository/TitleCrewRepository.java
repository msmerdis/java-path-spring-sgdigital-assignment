package gr.sgdigital.movies.repository;

import gr.sgdigital.common.repository.AbstractRepository;
import gr.sgdigital.movies.domain.TitleCrew;
import gr.sgdigital.movies.domain.TitleCrewKey;
import gr.sgdigital.movies.transfer.TitleCrewCreateDTO;
import gr.sgdigital.movies.transfer.TitleCrewDetailViewDTO;
import gr.sgdigital.movies.transfer.TitleCrewSimpleViewDTO;
import gr.sgdigital.movies.transfer.TitleCrewUpdateDTO;

public interface TitleCrewRepository extends AbstractRepository<TitleCrewKey, TitleCrew, TitleCrewCreateDTO, TitleCrewUpdateDTO, TitleCrewSimpleViewDTO, TitleCrewDetailViewDTO> {
}



