package gr.sgdigital.app.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import gr.sgdigital.common.base.BaseComponent;
import gr.sgdigital.common.transfer.ApiStatus;
import gr.sgdigital.movies.domain.TitleCrewKey;
import gr.sgdigital.movies.service.TitleCrewService;
import gr.sgdigital.movies.transfer.TitleCrewCreateDTO;
import gr.sgdigital.movies.transfer.TitleCrewDetailViewDTO;
import gr.sgdigital.movies.transfer.TitleCrewUpdateDTO;

@Component
@Profile("sanity")
public class TitleCrewSanityRunner extends BaseComponent implements CommandLineRunner {

	@Autowired private TitleCrewService titleCrewService;
	@Autowired private TitleSanityRunner titleSanityRunner;
	@Autowired private CrewSanityRunner crewSanityRunner;
	@Autowired private CrewRoleSanityRunner crewRoleSanityRunner;

	public class TitleCrewKeyInfo {
		public Long titleId;
		public Long crewId;
		public Integer crewRoleId;
		public Integer updateRoleId;
		public TitleCrewKey titleCrewId;
	}

	@Override
	public void run(String... args) throws Exception {
		// create a dummy titleCrew
		TitleCrewKeyInfo id = createTitleCrew ("ToUpdate");

		// verify the correct name is stored
		checkTitleCrew (id, "ToUpdate", "TitleCrew creation did not store the correct name");

		// update the titleCrew
		updateTitleCrew (id, "ToDelete");

		// verify the correct name is stored
		checkTitleCrew (id, "ToDelete", "TitleCrew update did not store the correct name");

		// delete the titleCrew
		deleteTitleCrew (id, "ToUpdate", "ToDelete");
	}

	private TitleCrewKeyInfo createTitleCrew (String roleName) throws ApiStatus, Exception {
		TitleCrewKeyInfo key = new TitleCrewKeyInfo();

		logger.info("creating data");
		key.titleId    = titleSanityRunner.createTitle("TitleToAddCrew", "TitleToAddCrewDescription");
		key.crewRoleId = crewRoleSanityRunner.createCrewRole(roleName);
		key.crewId     = crewSanityRunner.createCrew("CrewToAddToTitle", "CrewToAddToTitle", "CrewToAddToTitle", "1984-12-20");

		TitleCrewCreateDTO titleCrewDTO = new TitleCrewCreateDTO();

		titleCrewDTO.setCrewId(key.crewId);
		titleCrewDTO.setRole(roleName);
		titleCrewDTO.setTitleId(key.titleId);

		TitleCrewDetailViewDTO view = titleCrewService.create(titleCrewDTO);

		key.titleCrewId = view.getId();

		return key;
	}

	private void checkTitleCrew (TitleCrewKeyInfo id, String roleName, String error) throws ApiStatus, Exception {
		if (!titleCrewService.find(id.titleCrewId).getCrewRoleName().equals(roleName)) {
			throw new Exception (error);
		}
	}

	private void updateTitleCrew (TitleCrewKeyInfo id, String roleName) throws ApiStatus, Exception {
		TitleCrewUpdateDTO titleCrewDTO = new TitleCrewUpdateDTO();

		id.updateRoleId = crewRoleSanityRunner.createCrewRole(roleName);

		titleCrewDTO.setId(id.titleCrewId);
		titleCrewDTO.setRole(roleName);

		titleCrewService.update(titleCrewDTO);
	}

	private void deleteTitleCrew (TitleCrewKeyInfo id, String createRole, String updateRole) throws ApiStatus, Exception {
		titleCrewService.delete(id.titleCrewId);

		// verify the titleCrew is gone
		if (titleCrewService.exists(id.titleCrewId)) {
			throw new Exception ("TitleCrew delete did not actually delete the titleCrew");
		}

		// verify that the role / crew / title where not deleted
		titleSanityRunner.checkTitle(id.titleId, "Removing the title crew cascaded on title", "TitleToAddCrew", "TitleToAddCrewDescription");
		crewRoleSanityRunner.checkCrewRole(id.crewRoleId,   createRole, "Removing the title crew cascaded on the crew role");
		crewRoleSanityRunner.checkCrewRole(id.updateRoleId, updateRole, "Removing the title crew cascaded on the crew role");
		crewSanityRunner.checkCrew(id.crewId, "CrewToAddToTitle", "CrewToAddToTitle", "CrewToAddToTitle", "1984-12-20", "Removing the title crew cascaded on the crew");

		// delete resources created for this test
		titleSanityRunner.deleteTitle(id.titleId);
		crewRoleSanityRunner.deleteCrewRole(id.crewRoleId);
		crewRoleSanityRunner.deleteCrewRole(id.updateRoleId);
		crewSanityRunner.deleteCrew(id.crewId);
	}
}



