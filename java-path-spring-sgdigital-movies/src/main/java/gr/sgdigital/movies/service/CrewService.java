package gr.sgdigital.movies.service;

import gr.sgdigital.common.service.AbstractService;
import gr.sgdigital.common.transfer.status.NotFoundException;
import gr.sgdigital.movies.domain.Crew;
import gr.sgdigital.movies.domain.TitleCrew;
import gr.sgdigital.movies.transfer.CrewCreateDTO;
import gr.sgdigital.movies.transfer.CrewDetailViewDTO;
import gr.sgdigital.movies.transfer.CrewSimpleViewDTO;
import gr.sgdigital.movies.transfer.CrewUpdateDTO;

public interface CrewService extends AbstractService<Long, Crew, CrewCreateDTO, CrewUpdateDTO, CrewSimpleViewDTO, CrewDetailViewDTO> {

	void linkTitleCrew(TitleCrew titleCrew, Long crewId) throws NotFoundException;
}



