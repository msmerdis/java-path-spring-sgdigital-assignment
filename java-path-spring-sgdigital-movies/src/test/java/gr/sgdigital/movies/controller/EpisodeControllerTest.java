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
import gr.sgdigital.movies.service.TitleService;
import gr.sgdigital.movies.transfer.EpisodeCreateDTO;
import gr.sgdigital.movies.transfer.EpisodeDetailViewDTO;
import gr.sgdigital.movies.transfer.EpisodeSimpleViewDTO;
import gr.sgdigital.movies.transfer.EpisodeUpdateDTO;

@SpringBootTest
@AutoConfigureMockMvc
public class EpisodeControllerTest extends BaseTestController {
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

	@Test
	public void getAllEpisodes () throws Exception {
		List<EpisodeSimpleViewDTO> episodes = new LinkedList<EpisodeSimpleViewDTO> ();

		episodes.add(makeSimpleEpisode(1L, "Episode1", "Description1", 1, "Action"));
		episodes.add(makeSimpleEpisode(2L, "Episode2", "Description2", 2, "Drama", "Comedy"));

		when(episodeService.findAll()).thenReturn(episodes);

		mockMvc.perform(get("/api/episode"))
			.andExpect(status().isOk())
			.andExpect(content().json(generateSuccessJson(episodes)));
	}

	@Test
	public void getEpisode () throws Exception {
		EpisodeDetailViewDTO episode = makeDetailedEpisode(3L, "Episode3", "Description3", 3, "Action");

		when(episodeService.find(3L)).thenReturn(episode);

		mockMvc.perform(get("/api/episode/3"))
			.andExpect(status().isOk())
			.andExpect(content().json(generateSuccessJson(episode)));
	}

	@Test
	public void createEpisode () throws Exception {
		EpisodeDetailViewDTO episode = makeDetailedEpisode(4L, "Episode4", "Description4", 3, "Action");
		EpisodeCreateDTO body = makeCreateEpisode (1L, "Episode4", "Description4", 2, "Action");

		when(episodeService.create(body)).thenReturn(episode);

		mockMvc.perform(post("/api/episode").content(generateJson(body)).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated())
			.andExpect(content().json(generateCreatedJson(episode)));
	}

	@Test
	public void updateEpisode () throws Exception {
		EpisodeUpdateDTO body = makeUpdateEpisode (5L, "Episode5", "Description5", 1, "Comedy");

		mockMvc.perform(put("/api/episode").content(generateJson(body)).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNoContent());
	}

	@Test
	public void deleteEpisode () throws Exception {
		mockMvc.perform(delete("/api/episode/6"))
			.andExpect(status().isNoContent());
	}

	private EpisodeCreateDTO makeCreateEpisode (Long seasonId, String name, String desc, int order, String... genres) throws JsonProcessingException {
		EpisodeCreateDTO episode = new EpisodeCreateDTO ();

		episode.setEpisodeOrder(order);
		episode.setEpisodeName(name);
		episode.setEpisodeDesc(desc);
		episode.setDuration(order);
		episode.setSeasonId(seasonId);

		return episode;
	}

	private EpisodeUpdateDTO makeUpdateEpisode (Long id, String name, String desc, int order, String... genres) throws JsonProcessingException {
		EpisodeUpdateDTO episode = new EpisodeUpdateDTO ();

		episode.setId(id);
		episode.setEpisodeOrder(order);
		episode.setEpisodeName(name);
		episode.setEpisodeDesc(desc);
		episode.setDuration(2400 + order);

		return episode;
	}

	private EpisodeSimpleViewDTO makeSimpleEpisode (Long id, String name, String desc, int order, String... genres) {
		EpisodeSimpleViewDTO episode = new EpisodeSimpleViewDTO ();

		episode.setEpisodeId(id);
		episode.setOrder(order);
		episode.setEpisodeName(name);
		episode.setEpisodeDesc(desc);
		episode.setDuration(2400 + order);

		return episode;
	}

	private EpisodeDetailViewDTO makeDetailedEpisode (Long id, String name, String desc, int order, String... genres) {
		EpisodeDetailViewDTO episode = new EpisodeDetailViewDTO ();

		episode.setEpisodeId(id);
		episode.setOrder(order);
		episode.setEpisodeName(name);
		episode.setEpisodeDesc(desc);
		episode.setDuration(2400 + order);
		episode.setSerieGenre(Arrays.stream(genres).collect(Collectors.toSet()));

		return episode;
	}
}



