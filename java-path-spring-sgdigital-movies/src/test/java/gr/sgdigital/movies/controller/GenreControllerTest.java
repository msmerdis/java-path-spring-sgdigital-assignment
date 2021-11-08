package gr.sgdigital.movies.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import gr.sgdigital.movies.service.CrewService;
import gr.sgdigital.movies.service.EpisodeService;
import gr.sgdigital.movies.service.GenreService;
import gr.sgdigital.movies.service.MovieService;
import gr.sgdigital.movies.service.SeasonService;
import gr.sgdigital.movies.service.SerieService;
import gr.sgdigital.movies.service.TitleService;
import gr.sgdigital.movies.transfer.GenreCreateDTO;
import gr.sgdigital.movies.transfer.GenreDetailViewDTO;
import gr.sgdigital.movies.transfer.GenreSimpleViewDTO;
import gr.sgdigital.movies.transfer.GenreUpdateDTO;

@SpringBootTest
@AutoConfigureMockMvc
public class GenreControllerTest extends BaseTestController {
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
	public void getAllGenres () throws Exception {
		List<GenreSimpleViewDTO> genres = new LinkedList<GenreSimpleViewDTO> ();

		genres.add(makeSimpleGenre(1, "Genre1"));
		genres.add(makeSimpleGenre(2, "Genre2"));

		when(genreService.findAll()).thenReturn(genres);

		mockMvc.perform(get("/api/genre"))
			.andExpect(status().isOk())
			.andExpect(content().json(generateSuccessJson(genres)));
	}

	@Test
	public void getGenre () throws Exception {
		GenreDetailViewDTO genre = makeDetailedGenre(1, "Genre1");

		when(genreService.find(1)).thenReturn(genre);

		mockMvc.perform(get("/api/genre/1"))
			.andExpect(status().isOk())
			.andExpect(content().json(generateSuccessJson(genre)));
	}

	@Test
	public void createGenre () throws Exception {
		GenreDetailViewDTO genre = makeDetailedGenre(3, "Genre3");
		GenreCreateDTO body = makeCreateGenre ("Genre3");

		when(genreService.create(body)).thenReturn(genre);

		mockMvc.perform(post("/api/genre").content(generateJson(body)).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated())
			.andExpect(content().json(generateCreatedJson(genre)));
	}

	@Test
	public void updateGenre () throws Exception {
		GenreUpdateDTO body = makeUpdateGenre (4, "Genre4");

		mockMvc.perform(put("/api/genre").content(generateJson(body)).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNoContent());
	}

	@Test
	public void deleteGenre () throws Exception {
		mockMvc.perform(delete("/api/genre/4"))
			.andExpect(status().isNoContent());
	}

	private GenreCreateDTO makeCreateGenre (String name) throws JsonProcessingException {
		GenreCreateDTO genre = new GenreCreateDTO ();

		genre.setName(name);

		return genre;
	}

	private GenreUpdateDTO makeUpdateGenre (Integer id, String name) throws JsonProcessingException {
		GenreUpdateDTO genre = new GenreUpdateDTO ();

		genre.setId(id);
		genre.setName(name);

		return genre;
	}

	private GenreSimpleViewDTO makeSimpleGenre (Integer id, String name) {
		GenreSimpleViewDTO genre = new GenreSimpleViewDTO ();

		genre.setGenreId  (id);
		genre.setGenreName(name);

		return genre;
	}

	private GenreDetailViewDTO makeDetailedGenre (Integer id, String name) {
		GenreDetailViewDTO genre = new GenreDetailViewDTO ();

		genre.setGenreId  (id);
		genre.setGenreName(name);

		return genre;
	}
}



