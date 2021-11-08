package gr.sgdigital.movies.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;

import gr.sgdigital.common.base.BaseTestController;
import gr.sgdigital.movies.service.CrewRoleService;
import gr.sgdigital.movies.service.CrewService;
import gr.sgdigital.movies.service.EpisodeService;
import gr.sgdigital.movies.service.GenreService;
import gr.sgdigital.movies.service.MovieService;
import gr.sgdigital.movies.service.SeasonService;
import gr.sgdigital.movies.service.SerieService;
import gr.sgdigital.movies.service.TitleCrewService;
import gr.sgdigital.movies.service.TitleService;
import gr.sgdigital.movies.transfer.CrewCreateDTO;
import gr.sgdigital.movies.transfer.CrewDetailViewDTO;
import gr.sgdigital.movies.transfer.CrewSimpleViewDTO;
import gr.sgdigital.movies.transfer.CrewUpdateDTO;

@SpringBootTest
@AutoConfigureMockMvc
public class CrewControllerTest extends BaseTestController {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private GenreService genreService;

	@MockBean
	private TitleService titleService;

	@MockBean
	private MovieService movieService;

	@MockBean
	private SerieService serieService;

	@MockBean
	private SeasonService seasonService;

	@MockBean
	private EpisodeService episodeService;

	@MockBean
	private CrewService crewService;

	@MockBean
	private CrewRoleService crewRoleService;

	@MockBean
	private TitleCrewService titleCrewService;

	@Test
	public void getAllCrews () throws Exception {
		List<CrewSimpleViewDTO> crews = new LinkedList<CrewSimpleViewDTO> ();

		crews.add(makeSimpleCrew(1L, "Crew1", "Member", new Date()));
		crews.add(makeSimpleCrew(2L, "Crew2", "Member", new Date()));

		when(crewService.findAll()).thenReturn(crews);

		mockMvc.perform(get("/api/crew"))
			.andExpect(status().isOk())
			.andExpect(content().json(generateSuccessJson(crews)));
	}

	@Test
	public void getCrew () throws Exception {
		CrewDetailViewDTO crew = makeDetailedCrew(3L, "Crew3", "Member", new Date());

		when(crewService.find(1L)).thenReturn(crew);

		mockMvc.perform(get("/api/crew/1"))
			.andExpect(status().isOk())
			.andExpect(content().json(generateSuccessJson(crew)));
	}

	@Test
	public void createCrew () throws Exception {
		CrewDetailViewDTO crew = makeDetailedCrew(4L, "Crew4", "Member", new Date());
		CrewCreateDTO body = makeCreateCrew ("Crew4", "Member", new Date());

		when(crewService.create(body)).thenReturn(crew);

		mockMvc.perform(post("/api/crew").content(generateJson(body)).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated())
			.andExpect(content().json(generateCreatedJson(crew)));
	}

	@Test
	public void updateCrew () throws Exception {
		CrewUpdateDTO body = makeUpdateCrew (5L, "Crew5", "Member", new Date());

		mockMvc.perform(put("/api/crew").content(generateJson(body)).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNoContent());
	}

	@Test
	public void deleteCrew () throws Exception {
		mockMvc.perform(delete("/api/crew/6"))
			.andExpect(status().isNoContent());
	}

	private CrewCreateDTO makeCreateCrew (String firstname, String lastname, Date birthDate) throws JsonProcessingException {
		CrewCreateDTO crew = new CrewCreateDTO ();

		crew.setFirstName(firstname);
		crew.setLastName(lastname);
		crew.setBirthDate(birthDate);

		return crew;
	}

	private CrewUpdateDTO makeUpdateCrew (Long id, String firstname, String lastname, Date birthDate) throws JsonProcessingException {
		CrewUpdateDTO crew = new CrewUpdateDTO ();

		crew.setId(id);
		crew.setFirstName(firstname);
		crew.setLastName(lastname);
		crew.setBirthDate(birthDate);

		return crew;
	}

	private CrewSimpleViewDTO makeSimpleCrew (Long id, String firstname, String lastname, Date birthDate) {
		CrewSimpleViewDTO crew = new CrewSimpleViewDTO ();

		crew.setFirstName(firstname);
		crew.setLastName(lastname);
		crew.setBirthDate(birthDate);

		return crew;
	}

	private CrewDetailViewDTO makeDetailedCrew (Long id, String firstname, String lastname, Date birthDate) {
		CrewDetailViewDTO crew = new CrewDetailViewDTO ();

		crew.setFirstName(firstname);
		crew.setLastName(lastname);
		crew.setBirthDate(birthDate);

		return crew;
	}
}



