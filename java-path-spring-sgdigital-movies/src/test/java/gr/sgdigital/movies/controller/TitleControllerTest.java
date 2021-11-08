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
import gr.sgdigital.movies.service.CrewService;
import gr.sgdigital.movies.service.EpisodeService;
import gr.sgdigital.movies.service.GenreService;
import gr.sgdigital.movies.service.MovieService;
import gr.sgdigital.movies.service.SeasonService;
import gr.sgdigital.movies.service.SerieService;
import gr.sgdigital.movies.service.TitleService;
import gr.sgdigital.movies.transfer.TitleCreateDTO;
import gr.sgdigital.movies.transfer.TitleDetailViewDTO;
import gr.sgdigital.movies.transfer.TitleSimpleViewDTO;
import gr.sgdigital.movies.transfer.TitleUpdateDTO;

@SpringBootTest
@AutoConfigureMockMvc
public class TitleControllerTest extends BaseTestController {
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

	@Test
	public void getAllTitles () throws Exception {
		List<TitleSimpleViewDTO> titles = new LinkedList<TitleSimpleViewDTO> ();

		titles.add(makeSimpleTitle(1L, "Title1", "Description1", "Action"));
		titles.add(makeSimpleTitle(2L, "Title2", "Description2", "Drama", "Comedy"));

		when(titleService.findAll()).thenReturn(titles);

		mockMvc.perform(get("/api/title"))
			.andExpect(status().isOk())
			.andExpect(content().json(generateSuccessJson(titles)));
	}

	@Test
	public void getTitle () throws Exception {
		TitleDetailViewDTO title = makeDetailedTitle(3L, "Title3", "Description3", "Action");

		when(titleService.find(3L)).thenReturn(title);

		mockMvc.perform(get("/api/title/3"))
			.andExpect(status().isOk())
			.andExpect(content().json(generateSuccessJson(title)));
	}

	@Test
	public void createTitle () throws Exception {
		TitleDetailViewDTO title = makeDetailedTitle(4L, "Title4", "Description4", "Action");
		TitleCreateDTO body = makeCreateTitle ("Title4", "Description4", "Action");

		when(titleService.create(body)).thenReturn(title);

		mockMvc.perform(post("/api/title").content(generateJson(body)).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated())
			.andExpect(content().json(generateCreatedJson(title)));
	}

	@Test
	public void updateTitle () throws Exception {
		TitleUpdateDTO body = makeUpdateTitle (5L, "Title5", "Description5", "Comedy");

		mockMvc.perform(put("/api/title").content(generateJson(body)).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNoContent());
	}

	@Test
	public void deleteTitle () throws Exception {
		mockMvc.perform(delete("/api/title/6"))
			.andExpect(status().isNoContent());
	}

	private TitleCreateDTO makeCreateTitle (String name, String desc, String... genres) throws JsonProcessingException {
		TitleCreateDTO title = new TitleCreateDTO ();

		title.setTitleName(name);
		title.setTitleDesc(desc);
		title.setGenres(Arrays.stream(genres).collect(Collectors.toSet()));

		return title;
	}

	private TitleUpdateDTO makeUpdateTitle (Long id, String name, String desc, String... genres) throws JsonProcessingException {
		TitleUpdateDTO title = new TitleUpdateDTO ();

		title.setId(id);
		title.setTitleName(name);
		title.setTitleDesc(desc);
		title.setGenres(Arrays.stream(genres).collect(Collectors.toSet()));

		return title;
	}

	private TitleSimpleViewDTO makeSimpleTitle (Long id, String name, String desc, String... genres) {
		TitleSimpleViewDTO title = new TitleSimpleViewDTO ();

		title.setTitleId(id);
		title.setTitleName(name);
		title.setTitleDesc(desc);
		title.setGenres(Arrays.stream(genres).collect(Collectors.toSet()));

		return title;
	}

	private TitleDetailViewDTO makeDetailedTitle (Long id, String name, String desc, String... genres) {
		TitleDetailViewDTO title = new TitleDetailViewDTO ();

		title.setTitleId(id);
		title.setTitleName(name);
		title.setTitleDesc(desc);
		title.setGenres(Arrays.stream(genres).collect(Collectors.toSet()));

		return title;
	}
}



