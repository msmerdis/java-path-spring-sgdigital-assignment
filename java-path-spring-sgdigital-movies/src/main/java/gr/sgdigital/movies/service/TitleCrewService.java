package gr.sgdigital.movies.service;

import gr.sgdigital.common.service.AbstractService;
import gr.sgdigital.movies.domain.TitleCrew;
import gr.sgdigital.movies.domain.TitleCrewKey;
import gr.sgdigital.movies.transfer.TitleCrewCreateDTO;
import gr.sgdigital.movies.transfer.TitleCrewDetailViewDTO;
import gr.sgdigital.movies.transfer.TitleCrewSimpleViewDTO;
import gr.sgdigital.movies.transfer.TitleCrewUpdateDTO;

public interface TitleCrewService extends AbstractService<TitleCrewKey, TitleCrew, TitleCrewCreateDTO, TitleCrewUpdateDTO, TitleCrewSimpleViewDTO, TitleCrewDetailViewDTO> {
}



