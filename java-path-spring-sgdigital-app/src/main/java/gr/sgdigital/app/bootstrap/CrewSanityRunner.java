package gr.sgdigital.app.bootstrap;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import gr.sgdigital.common.base.BaseComponent;
import gr.sgdigital.common.transfer.ApiStatus;
import gr.sgdigital.movies.base.Formats;
import gr.sgdigital.movies.service.CrewService;
import gr.sgdigital.movies.transfer.CrewCreateDTO;
import gr.sgdigital.movies.transfer.CrewDetailViewDTO;
import gr.sgdigital.movies.transfer.CrewUpdateDTO;

@Component
@Profile("sanity")
public class CrewSanityRunner extends BaseComponent implements CommandLineRunner {

	@Autowired private CrewService crewService;

	@Override
	public void run(String... args) throws Exception {
		// create a dummy crew
		long id = createCrew ("FirstNameToUpdate", "LastNameToUpdate", "MiddleNameToUpdate", "1984-12-20");

		// verify the correct data are stored
		checkCrew (id, "FirstNameToUpdate", "LastNameToUpdate", "MiddleNameToUpdate", "1984-12-20", "Crew creation did not store correct data");

		// update the crew
		updateCrew (id, "FirstNameToDelete", "LastNameToDelete", "MiddleNameToDelete", "1988-02-28");

		// verify the correct name is stored
		checkCrew (id, "FirstNameToDelete", "LastNameToDelete", "MiddleNameToDelete", "1988-02-28", "Crew update did not store correct data");

		// delete the crew
		crewService.delete(id);

		// verify the crew is gone
		if (crewService.exists(id)) {
			throw new Exception ("Crew delete did not actually delete the crew");
		}
	}

	private long createCrew (String firstname, String lastname, String middlename, String birthDate) throws ApiStatus, Exception {
		CrewCreateDTO crewDTO = new CrewCreateDTO();
  
		crewDTO.setFirstName(firstname);
		crewDTO.setLastName(lastname);
		crewDTO.setMiddleName(middlename);
		crewDTO.setBirthDate(new SimpleDateFormat(Formats.DATE_FORMAT).parse(birthDate));

		CrewDetailViewDTO view = crewService.create(crewDTO);
		return view.getCrewId();
	}

	private void checkCrew (long id, String firstname, String lastname, String middlename, String birthDate, String error) throws ApiStatus, Exception {
		CrewDetailViewDTO dto = crewService.find(id);

		if (!dto.getFirstName().equals(firstname)) {
			throw new Exception (error);
		}
		if (!dto.getLastName().equals(lastname)) {
			throw new Exception (error);
		}
		if (!dto.getMiddleName().equals(middlename)) {
			throw new Exception (error);
		}
		if (!new SimpleDateFormat(Formats.DATE_FORMAT).format(dto.getBirthDate()).equals(birthDate)) {
			throw new Exception (error);
		}
	}

	private void updateCrew (long id, String firstname, String lastname, String middlename, String birthDate) throws ApiStatus, Exception {
		CrewUpdateDTO crewDTO = new CrewUpdateDTO();

		crewDTO.setId(id);

		crewDTO.setFirstName(firstname);
		crewDTO.setLastName(lastname);
		crewDTO.setMiddleName(middlename);
		crewDTO.setBirthDate(new SimpleDateFormat(Formats.DATE_FORMAT).parse(birthDate));

		crewService.update(crewDTO);
	}
}



