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
import gr.sgdigital.movies.transfer.MovieCreateDTO;
import gr.sgdigital.movies.transfer.MovieDetailViewDTO;
import gr.sgdigital.movies.transfer.MovieSimpleViewDTO;
import gr.sgdigital.movies.transfer.MovieUpdateDTO;

@SpringBootTest
@AutoConfigureMockMvc
public class MovieControllerTest extends BaseTestController {
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
	public void getAllMovies () throws Exception {
		List<MovieSimpleViewDTO> movies = new LinkedList<MovieSimpleViewDTO> ();

		movies.add(makeSimpleMovie(1L, "Action"));
		movies.add(makeSimpleMovie(1L, "Drama", "Comedy"));

		when(movieService.findAll()).thenReturn(movies);

		mockMvc.perform(get("/api/movie"))
			.andExpect(status().isOk())
			.andExpect(content().json(generateSuccessJson(movies)));
	}

	@Test
	public void getMovie () throws Exception {
		MovieDetailViewDTO movie = makeDetailedMovie(3L, "Action");

		when(movieService.find(3L)).thenReturn(movie);

		mockMvc.perform(get("/api/movie/3"))
			.andExpect(status().isOk())
			.andExpect(content().json(generateSuccessJson(movie)));
	}

	@Test
	public void createMovie () throws Exception {
		MovieDetailViewDTO movie = makeDetailedMovie(4L, "Action");
		MovieCreateDTO body = makeCreateMovie (4L, "Action");

		when(movieService.create(body)).thenReturn(movie);

		mockMvc.perform(post("/api/movie").content(generateJson(body)).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated())
			.andExpect(content().json(generateCreatedJson(movie)));
	}

	@Test
	public void updateMovie () throws Exception {
		MovieUpdateDTO body = makeUpdateMovie (5L, "Comedy");

		mockMvc.perform(put("/api/movie").content(generateJson(body)).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNoContent());
	}

	@Test
	public void deleteMovie () throws Exception {
		mockMvc.perform(delete("/api/movie/6"))
			.andExpect(status().isNoContent());
	}

	private MovieCreateDTO makeCreateMovie (Long id, String... genres) throws JsonProcessingException {
		MovieCreateDTO movie = new MovieCreateDTO ();

		movie.setMovieName("Movie Title " + id);
		movie.setMovieDesc("Movie description " + id);
		movie.setReleasedYear((int)(2000 + id));
		movie.setGenres(Arrays.stream(genres).collect(Collectors.toSet()));

		return movie;
	}

	private MovieUpdateDTO makeUpdateMovie (Long id, String... genres) throws JsonProcessingException {
		MovieUpdateDTO movie = new MovieUpdateDTO ();

		movie.setId(id);
		movie.setMovieName("Movie Title " + id);
		movie.setMovieDesc("Movie description " + id);
		movie.setReleasedYear((int)(2000 + id));
		movie.setGenres(Arrays.stream(genres).collect(Collectors.toSet()));

		return movie;
	}

	private MovieSimpleViewDTO makeSimpleMovie (Long id, String... genres) {
		MovieSimpleViewDTO movie = new MovieSimpleViewDTO ();

		movie.setMovieId(id);
		movie.setMovieName("Movie Title " + id);
		movie.setMovieDesc("Movie description " + id);
		movie.setReleasedYear((int)(2000 + id));
		movie.setGenres(Arrays.stream(genres).collect(Collectors.toSet()));

		return movie;
	}

	private MovieDetailViewDTO makeDetailedMovie (Long id, String... genres) {
		MovieDetailViewDTO movie = new MovieDetailViewDTO ();

		movie.setMovieId(id);
		movie.setMovieName("Movie Title " + id);
		movie.setMovieDesc("Movie description " + id);
		movie.setReleasedYear((int)(2000 + id));
		movie.setGenres(Arrays.stream(genres).collect(Collectors.toSet()));

		return movie;
	}
}



