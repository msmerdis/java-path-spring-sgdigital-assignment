package gr.sgdigital.movies.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gr.sgdigital.common.controller.AbstractController;
import gr.sgdigital.movies.domain.CrewRole;
import gr.sgdigital.movies.service.CrewRoleService;
import gr.sgdigital.movies.transfer.CrewRoleCreateDTO;
import gr.sgdigital.movies.transfer.CrewRoleDetailViewDTO;
import gr.sgdigital.movies.transfer.CrewRoleSimpleViewDTO;
import gr.sgdigital.movies.transfer.CrewRoleUpdateDTO;

@RestController
@RequestMapping("/api/crewrole")
public class CrewRoleController extends AbstractController<
	Integer,
	CrewRole,
	CrewRoleCreateDTO,
	CrewRoleUpdateDTO,
	CrewRoleSimpleViewDTO,
	CrewRoleDetailViewDTO,
	CrewRoleService
> {

	@Autowired
	public CrewRoleController(CrewRoleService service) {
		super(service);
	}

}



