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
import gr.sgdigital.movies.service.EpisodeService;
import gr.sgdigital.movies.service.GenreService;
import gr.sgdigital.movies.service.MovieService;
import gr.sgdigital.movies.service.SeasonService;
import gr.sgdigital.movies.service.SerieService;
import gr.sgdigital.movies.service.TitleService;
import gr.sgdigital.movies.transfer.SeasonCreateDTO;
import gr.sgdigital.movies.transfer.SeasonDetailViewDTO;
import gr.sgdigital.movies.transfer.SeasonSimpleViewDTO;
import gr.sgdigital.movies.transfer.SeasonUpdateDTO;

@SpringBootTest
@AutoConfigureMockMvc
public class SeasonControllerTest extends BaseTestController {
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

	@Test
	public void getAllSeasons () throws Exception {
		List<SeasonSimpleViewDTO> seasons = new LinkedList<SeasonSimpleViewDTO> ();

		seasons.add(makeSimpleSeason(1L, "Season1", "Description1", "Action"));
		seasons.add(makeSimpleSeason(2L, "Season2", "Description2", "Drama", "Comedy"));

		when(seasonService.findAll()).thenReturn(seasons);

		mockMvc.perform(get("/api/season"))
			.andExpect(status().isOk())
			.andExpect(content().json(generateSuccessJson(seasons)));
	}

	@Test
	public void getSeason () throws Exception {
		SeasonDetailViewDTO season = makeDetailedSeason(3L, "Season3", "Description3", "Action");

		when(seasonService.find(3L)).thenReturn(season);

		mockMvc.perform(get("/api/season/3"))
			.andExpect(status().isOk())
			.andExpect(content().json(generateSuccessJson(season)));
	}

	@Test
	public void createSeason () throws Exception {
		SeasonDetailViewDTO season = makeDetailedSeason(4L, "Season4", "Description4", "Action");
		SeasonCreateDTO body = makeCreateSeason (1L, "Season4", "Description4", 2, "Action");

		when(seasonService.create(body)).thenReturn(season);

		mockMvc.perform(post("/api/season").content(generateJson(body)).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated())
			.andExpect(content().json(generateCreatedJson(season)));
	}

	@Test
	public void updateSeason () throws Exception {
		SeasonUpdateDTO body = makeUpdateSeason (5L, "Season5", "Description5", 1, "Comedy");

		mockMvc.perform(put("/api/season").content(generateJson(body)).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNoContent());
	}

	@Test
	public void deleteSeason () throws Exception {
		mockMvc.perform(delete("/api/season/6"))
			.andExpect(status().isNoContent());
	}

	private SeasonCreateDTO makeCreateSeason (Long seriesId, String name, String desc, int order, String... genres) throws JsonProcessingException {
		SeasonCreateDTO season = new SeasonCreateDTO ();

		season.setSeasonOrder(order);
		season.setSeasonName(name);
		season.setSeasonDesc(desc);
		season.setReleasedYear(2000 + order);
		season.setSeriesId(seriesId);

		return season;
	}

	private SeasonUpdateDTO makeUpdateSeason (Long id, String name, String desc, int order, String... genres) throws JsonProcessingException {
		SeasonUpdateDTO season = new SeasonUpdateDTO ();

		season.setId(id);
		season.setSeasonOrder(order);
		season.setSeasonName(name);
		season.setSeasonDesc(desc);
		season.setReleasedYear(2000 + order);

		return season;
	}

	private SeasonSimpleViewDTO makeSimpleSeason (Long id, String name, String desc, String... genres) {
		SeasonSimpleViewDTO season = new SeasonSimpleViewDTO ();

		season.setSeasonId(id);
		season.setSeasonName(name);
		season.setSeasonDesc(desc);

		return season;
	}

	private SeasonDetailViewDTO makeDetailedSeason (Long id, String name, String desc, String... genres) {
		SeasonDetailViewDTO season = new SeasonDetailViewDTO ();

		season.setSeasonId(id);
		season.setSeasonName(name);
		season.setSeasonDesc(desc);
		season.setSerieGenre(Arrays.stream(genres).collect(Collectors.toSet()));

		return season;
	}
}



