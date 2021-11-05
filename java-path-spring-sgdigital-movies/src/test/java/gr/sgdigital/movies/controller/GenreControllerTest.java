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
import gr.sgdigital.movies.domain.Genre;
import gr.sgdigital.movies.service.GenreService;
import gr.sgdigital.movies.service.MovieService;

@SpringBootTest
@AutoConfigureMockMvc
public class GenreControllerTest extends BaseTestController {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private GenreService genreService;

	@MockBean
	private MovieService moviesService;

	@Test
	public void getAllGenres () throws Exception {
		List<Genre> genres = new LinkedList<Genre> ();

		genres.add(makeGenre(1, "Genre1"));
		genres.add(makeGenre(2, "Genre2"));

		when(genreService.findAll()).thenReturn(genres);

		mockMvc.perform(get("/api/genre"))
			.andExpect(status().isOk())
			.andExpect(content().json(generateSuccessJson(genres)));
	}

	@Test
	public void getGenre () throws Exception {
		Genre genre = makeGenre(1, "Genre1");

		when(genreService.find(1)).thenReturn(genre);

		mockMvc.perform(get("/api/genre/1"))
			.andExpect(status().isOk())
			.andExpect(content().json(generateSuccessJson(genre)));
	}

	private Genre makeGenre (Integer id, String name) {
		Genre genre = new Genre ();

		genre.setId(id);
		genre.setName(name);

		return genre;
	}
}



