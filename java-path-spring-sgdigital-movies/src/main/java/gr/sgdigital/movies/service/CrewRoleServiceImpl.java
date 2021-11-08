package gr.sgdigital.movies.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.sgdigital.common.service.BaseServiceImpl;
import gr.sgdigital.movies.domain.CrewRole;
import gr.sgdigital.movies.repository.CrewRoleRepository;
import gr.sgdigital.movies.transfer.CrewRoleCreateDTO;
import gr.sgdigital.movies.transfer.CrewRoleDetailViewDTO;
import gr.sgdigital.movies.transfer.CrewRoleSimpleViewDTO;
import gr.sgdigital.movies.transfer.CrewRoleUpdateDTO;

@Service
public class CrewRoleServiceImpl extends BaseServiceImpl<
	Integer,
	CrewRole,
	CrewRoleCreateDTO,
	CrewRoleUpdateDTO,
	CrewRoleSimpleViewDTO,
	CrewRoleDetailViewDTO,
	CrewRoleRepository
> implements CrewRoleService {

	@Autowired
	public CrewRoleServiceImpl(CrewRoleRepository repository) {
		super(repository, CrewRole.class);
	}

}



