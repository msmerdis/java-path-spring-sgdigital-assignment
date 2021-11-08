package gr.sgdigital.movies.service;

import gr.sgdigital.common.service.BaseService;
import gr.sgdigital.movies.domain.Crew;
import gr.sgdigital.movies.transfer.CrewCreateDTO;
import gr.sgdigital.movies.transfer.CrewDetailViewDTO;
import gr.sgdigital.movies.transfer.CrewSimpleViewDTO;
import gr.sgdigital.movies.transfer.CrewUpdateDTO;

public interface CrewService extends BaseService<Long, Crew, CrewCreateDTO, CrewUpdateDTO, CrewSimpleViewDTO, CrewDetailViewDTO> {
}



