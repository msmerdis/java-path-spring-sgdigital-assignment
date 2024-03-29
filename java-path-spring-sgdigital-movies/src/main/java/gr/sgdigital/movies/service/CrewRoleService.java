package gr.sgdigital.movies.service;

import gr.sgdigital.common.service.AbstractService;
import gr.sgdigital.common.transfer.status.NotFoundException;
import gr.sgdigital.movies.domain.CrewRole;
import gr.sgdigital.movies.domain.TitleCrew;
import gr.sgdigital.movies.transfer.CrewRoleCreateDTO;
import gr.sgdigital.movies.transfer.CrewRoleDetailViewDTO;
import gr.sgdigital.movies.transfer.CrewRoleSimpleViewDTO;
import gr.sgdigital.movies.transfer.CrewRoleUpdateDTO;

public interface CrewRoleService extends AbstractService<Integer, CrewRole, CrewRoleCreateDTO, CrewRoleUpdateDTO, CrewRoleSimpleViewDTO, CrewRoleDetailViewDTO> {

	void linkTitleCrew(TitleCrew titleCrew, String role) throws NotFoundException;
}



