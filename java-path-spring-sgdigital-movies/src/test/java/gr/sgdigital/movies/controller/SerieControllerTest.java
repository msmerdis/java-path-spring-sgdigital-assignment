package gr.sgdigital.movies.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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
import gr.sgdigital.movies.transfer.SerieCreateDTO;
import gr.sgdigital.movies.transfer.SerieDetailViewDTO;
import gr.sgdigital.movies.transfer.SerieSimpleViewDTO;
import gr.sgdigital.movies.transfer.SerieUpdateDTO;

@SpringBootTest
@AutoConfigureMockMvc
public class SerieControllerTest extends BaseTestController {
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
	public void getAllSeries () throws Exception {
		List<SerieSimpleViewDTO> series = new LinkedList<SerieSimpleViewDTO> ();

		series.add(makeSimpleSerie(1L, "Action"));
		series.add(makeSimpleSerie(1L, "Drama", "Comedy"));

		when(serieService.findAll()).thenReturn(series);

		mockMvc.perform(get("/api/serie"))
			.andExpect(status().isOk())
			.andExpect(content().json(generateSuccessJson(series)));
	}

	@Test
	public void getSerie () throws Exception {
		SerieDetailViewDTO serie = makeDetailedSerie(3L, "Action");

		when(serieService.find(3L)).thenReturn(serie);

		mockMvc.perform(get("/api/serie/3"))
			.andExpect(status().isOk())
			.andExpect(content().json(generateSuccessJson(serie)));
	}

	@Test
	public void createSerie () throws Exception {
		SerieDetailViewDTO serie = makeDetailedSerie(4L, "Action");
		SerieCreateDTO body = makeCreateSerie (4L, "Action");

		when(serieService.create(body)).thenReturn(serie);

		mockMvc.perform(post("/api/serie").content(generateJson(body)).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated())
			.andExpect(content().json(generateCreatedJson(serie)));
	}

	@Test
	public void updateSerie () throws Exception {
		SerieUpdateDTO body = makeUpdateSerie (5L, "Comedy");

		mockMvc.perform(put("/api/serie").content(generateJson(body)).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNoContent());
	}

	@Test
	public void deleteSerie () throws Exception {
		mockMvc.perform(delete("/api/serie/6"))
			.andExpect(status().isNoContent());
	}

	private SerieCreateDTO makeCreateSerie (Long id, String... genres) throws JsonProcessingException {
		SerieCreateDTO serie = new SerieCreateDTO ();

		serie.setSerieName("Serie Title " + id);
		serie.setSerieDesc("Serie description " + id);
		serie.setOngoing(id % 2 == 0);
		serie.setGenres(Arrays.stream(genres).collect(Collectors.toSet()));

		return serie;
	}

	private SerieUpdateDTO makeUpdateSerie (Long id, String... genres) throws JsonProcessingException {
		SerieUpdateDTO serie = new SerieUpdateDTO ();

		serie.setId(id);
		serie.setSerieName("Serie Title " + id);
		serie.setSerieDesc("Serie description " + id);
		serie.setOngoing(id % 2 == 0);
		serie.setGenres(Arrays.stream(genres).collect(Collectors.toSet()));

		return serie;
	}

	private SerieSimpleViewDTO makeSimpleSerie (Long id, String... genres) {
		SerieSimpleViewDTO serie = new SerieSimpleViewDTO ();

		serie.setSerieId(id);
		serie.setSerieName("Serie Title " + id);
		serie.setSerieDesc("Serie description " + id);
		serie.setOngoing(id % 2 == 0);
		serie.setGenres(Arrays.stream(genres).collect(Collectors.toSet()));

		return serie;
	}

	private SerieDetailViewDTO makeDetailedSerie (Long id, String... genres) {
		SerieDetailViewDTO serie = new SerieDetailViewDTO ();

		serie.setSerieId(id);
		serie.setSerieName("Serie Title " + id);
		serie.setSerieDesc("Serie description " + id);
		serie.setOngoing(id % 2 == 0);
		serie.setGenres(Arrays.stream(genres).collect(Collectors.toSet()));

		return serie;
	}
}



