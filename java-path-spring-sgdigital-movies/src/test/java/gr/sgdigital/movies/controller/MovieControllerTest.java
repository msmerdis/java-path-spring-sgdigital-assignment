package gr.sgdigital.movies.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import gr.sgdigital.common.base.BaseTestController;
import gr.sgdigital.movies.domain.Movie;
import gr.sgdigital.movies.service.GenreService;
import gr.sgdigital.movies.service.MovieService;

@SpringBootTest
@AutoConfigureMockMvc
public class MovieControllerTest extends BaseTestController {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private GenreService genreService;

	@MockBean
	private MovieService moviesService;

	@Test
	public void getAllMovies () throws Exception {
		List<Movie> movies = new LinkedList<Movie> ();

		movies.add(makeMovie(1L, "Movie1"));
		movies.add(makeMovie(2L, "Movie2"));

		when(moviesService.findAll()).thenReturn(movies);

		mockMvc.perform(get("/api/movie"))
			.andExpect(status().isOk())
			.andExpect(content().json(generateSuccessJson(movies)));
	}

	@Test
	public void getGenre () throws Exception {
		Movie movie = makeMovie(1L, "Movie1");

		when(moviesService.find(1L)).thenReturn(movie);

		mockMvc.perform(get("/api/movie/1"))
			.andExpect(status().isOk())
			.andExpect(content().json(generateSuccessJson(movie)));
	}

	private Movie makeMovie (Long id, String title) {
		Movie movie = new Movie ();

		movie.setId(id);
		movie.setTitle(title);

		return movie;
	}
}



