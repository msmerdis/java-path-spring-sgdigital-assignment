package gr.sgdigital.app.bootstrap;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import gr.sgdigital.common.base.BaseComponent;
import gr.sgdigital.common.transfer.ApiStatus;
import gr.sgdigital.movies.service.MovieService;
import gr.sgdigital.movies.transfer.MovieCreateDTO;
import gr.sgdigital.movies.transfer.MovieDetailViewDTO;
import gr.sgdigital.movies.transfer.MovieUpdateDTO;

@Component
@Profile("sanity")
public class MovieSanityRunner extends BaseComponent implements CommandLineRunner {

	@Autowired private MovieService movieService;

	@Override
	public void run(String... args) throws Exception {
		// create a dummy movie
		long id = createMovie ("ToUpdate", "ToUpdateDesc", 2000, "Action", "Comedy");

		// verify the correct name is stored
		checkMovie (id, "Movie creation did not store the correct name", "ToUpdate", "ToUpdateDesc", 2000, "Action", "Comedy");

		// update the movie
		updateMovie (id, "ToAddGenre", "ToAddGenreDesc", 2001, "Action", "Comedy");

		// verify the correct name is stored
		checkMovie (id, "Movie update did not store the correct value", "ToAddGenre", "ToAddGenreDesc", 2001, "Action", "Comedy");

		// add a new genre
		updateMovie (id, "ToDelete", "ToDeleteDesc", 2001, "Comedy", "Horror");

		// verify the genre is stored
		checkMovie (id, "Movie update did not store the correct value", "ToDelete", "ToDeleteDesc", 2001, "Comedy", "Horror");

		// delete the movie
		movieService.delete(id);

		// verify the movie is gone
		if (movieService.exists(id)) {
			throw new Exception ("Movie delete did not actually delete the movie");
		}
	}

	private long createMovie (String movie, String descr, int year, String... genres) throws ApiStatus, Exception {
		MovieCreateDTO movieDTO = new MovieCreateDTO();
		movieDTO.setMovieName(movie);
		movieDTO.setMovieDesc(descr);
		movieDTO.setReleasedYear(year);
		movieDTO.setGenres(Arrays.stream(genres).collect(Collectors.toSet()));
		MovieDetailViewDTO view = movieService.create(movieDTO);

		return view.getMovieId();
	}

	private void checkMovie (long id, String error, String movie, String descr, int year, String... genres) throws ApiStatus, Exception {
		MovieDetailViewDTO theMovie = movieService.find(id);
		Set<String> genreSet = Arrays.stream(genres).collect(Collectors.toSet());

		if (theMovie.getMovieId().longValue() != id) {
			throw new Exception (error);
		}
		if (!theMovie.getMovieName().equals(movie)) {
			throw new Exception (error + " (2)");
		}
		if (!theMovie.getMovieDesc().equals(descr)) {
			throw new Exception (error + " (3)");
		}
		if (theMovie.getReleasedYear() != year) {
			throw new Exception (error + " (4)");
		}
		if (!theMovie.getGenres().containsAll(genreSet)) {
			throw new Exception (error + " (5)");
		}
		if (!genreSet.containsAll(theMovie.getGenres())) {
			throw new Exception (error + " (6)");
		}
	}

	private void updateMovie (long id, String movie, String descr, int year, String... genres) throws ApiStatus, Exception {
		MovieUpdateDTO movieDTO = new MovieUpdateDTO();
		movieDTO.setId(id);
		movieDTO.setMovieName(movie);
		movieDTO.setMovieDesc(descr);
		movieDTO.setReleasedYear(year);
		movieDTO.setGenres(Arrays.stream(genres).collect(Collectors.toSet()));
		movieService.update(movieDTO);
	}
}



