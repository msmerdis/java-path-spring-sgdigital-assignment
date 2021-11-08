package gr.sgdigital.app.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import gr.sgdigital.common.base.BaseComponent;
import gr.sgdigital.common.transfer.ApiStatus;
import gr.sgdigital.movies.service.CrewRoleService;
import gr.sgdigital.movies.transfer.CrewRoleCreateDTO;
import gr.sgdigital.movies.transfer.CrewRoleDetailViewDTO;
import gr.sgdigital.movies.transfer.CrewRoleUpdateDTO;

@Component
@Profile("sanity")
public class CrewRoleSanityRunner extends BaseComponent implements CommandLineRunner {

	@Autowired private CrewRoleService crewRoleService;

	@Override
	public void run(String... args) throws Exception {
		// create a dummy crewRole
		int id = createCrewRole ("ToUpdate");

		// verify the correct name is stored
		checkCrewRole (id, "ToUpdate", "CrewRole creation did not store the correct name");

		// update the crewRole
		updateCrewRole (id, "ToDelete");

		// verify the correct name is stored
		checkCrewRole (id, "ToDelete", "CrewRole update did not store the correct name");

		// delete the crewRole
		deleteCrewRole (id);
	}

	public int createCrewRole (String crewRole) throws ApiStatus, Exception {
		CrewRoleCreateDTO crewRoleDTO = new CrewRoleCreateDTO();
		crewRoleDTO.setName(crewRole);
		CrewRoleDetailViewDTO view = crewRoleService.create(crewRoleDTO);
		return view.getCrewRoleId();
	}

	public void checkCrewRole (int id, String crewRole, String error) throws ApiStatus, Exception {
		if (!crewRoleService.find(id).getCrewRoleName().equals(crewRole)) {
			throw new Exception (error);
		}
	}

	private void updateCrewRole (int id, String crewRole) throws ApiStatus, Exception {
		CrewRoleUpdateDTO crewRoleDTO = new CrewRoleUpdateDTO();
		crewRoleDTO.setId(id);
		crewRoleDTO.setName(crewRole);
		crewRoleService.update(crewRoleDTO);
	}

	public void deleteCrewRole (int id) throws Exception {
		crewRoleService.delete(id);

		// verify the crewRole is gone
		if (crewRoleService.exists(id)) {
			throw new Exception ("CrewRole delete did not actually delete the crewRole");
		}
	}
}



