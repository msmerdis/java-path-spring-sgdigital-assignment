package gr.sgdigital.app.bootstrap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import gr.sgdigital.common.base.BaseComponent;
import gr.sgdigital.movies.domain.Genre;
import gr.sgdigital.movies.domain.Movie;
import gr.sgdigital.movies.service.GenreService;
import gr.sgdigital.movies.service.MovieService;

@Component
public class GenerateContentRunner extends BaseComponent implements CommandLineRunner {

	@Autowired private GenreService genreService;
	@Autowired private MovieService movieService;

	@Override
	public void run(String... args) {
		generateGenres ();
		generateMovies ();
	}

	private void generateGenres () {
		generateGenre ("Action");
		generateGenre ("Adventure");
		generateGenre ("Drama");
		generateGenre ("Comedy");
		generateGenre ("Action");
		generateGenre ("Fantasy");
		generateGenre ("Horror");
		generateGenre ("Romance");
		generateGenre ("Western");
		generateGenre ("Thriller");
		generateGenre ("Mystery");
	}

	private void generateGenre (String name) {
		Genre genre = genreService.findByName(name);

		if (genre == null) {
			genre = new Genre ();
			genre.setName(name);
			genreService.create(genre);
		}
	}

	private void generateMovies () {
		generateMovie (
			"Fight Club",
			"An insomniac office worker and a devil-may-care soap maker form an underground fight club that evolves into much more.",
			1999,
			genreService.findByName("Drama")
		);
		generateMovie (
			"Pirates of the Caribbean: The Curse of the Black Pearl",
			"Blacksmith Will Turner teams up with eccentric pirate \"Captain\" Jack Sparrow to save his love, the governor's daughter, from Jack's former pirate allies, who are now undead.",
			2003,
			genreService.findByName("Action"),
			genreService.findByName("Adventure"),
			genreService.findByName("Fantasy")
		);
		generateMovie (
			"The Lord of the Rings: The Fellowship of the Ring",
			"A meek Hobbit from the Shire and eight companions set out on a journey to destroy the powerful One Ring and save Middle-earth from the Dark Lord Sauron.",
			2001,
			genreService.findByName("Action"),
			genreService.findByName("Adventure"),
			genreService.findByName("Drama")
		);
	}

	private void generateMovie (String title, String desc, int year, Genre... genres) {
		Movie movie = movieService.findByTitle(title);

		if (movie == null) {
			movie = new Movie ();
			movie.setTitle(title);
			movie.setDescription(desc);
			movie.setReleasedYear(year);
			movieService.create(movie);
		}
	}
}



