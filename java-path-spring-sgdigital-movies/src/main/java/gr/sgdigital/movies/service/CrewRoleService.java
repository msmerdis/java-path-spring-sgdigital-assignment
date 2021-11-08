package gr.sgdigital.movies.service;

import gr.sgdigital.common.service.BaseService;
import gr.sgdigital.movies.domain.CrewRole;
import gr.sgdigital.movies.transfer.CrewRoleCreateDTO;
import gr.sgdigital.movies.transfer.CrewRoleDetailViewDTO;
import gr.sgdigital.movies.transfer.CrewRoleSimpleViewDTO;
import gr.sgdigital.movies.transfer.CrewRoleUpdateDTO;

public interface CrewRoleService extends BaseService<Integer, CrewRole, CrewRoleCreateDTO, CrewRoleUpdateDTO, CrewRoleSimpleViewDTO, CrewRoleDetailViewDTO> {
}



