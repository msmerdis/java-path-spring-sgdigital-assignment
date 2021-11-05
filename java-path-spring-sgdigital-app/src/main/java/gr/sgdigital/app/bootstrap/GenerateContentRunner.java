package gr.sgdigital.app.bootstrap;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import gr.sgdigital.common.base.BaseComponent;
import gr.sgdigital.movies.domain.Episode;
import gr.sgdigital.movies.domain.Genre;
import gr.sgdigital.movies.domain.Movie;
import gr.sgdigital.movies.domain.Season;
import gr.sgdigital.movies.domain.Serie;
import gr.sgdigital.movies.service.EpisodeService;
import gr.sgdigital.movies.service.GenreService;
import gr.sgdigital.movies.service.MovieService;
import gr.sgdigital.movies.service.SeasonService;
import gr.sgdigital.movies.service.SerieService;

@Component
public class GenerateContentRunner extends BaseComponent implements CommandLineRunner {

	@Autowired private GenreService genreService;
	@Autowired private MovieService movieService;
	@Autowired private SerieService serieService;
	@Autowired private SeasonService seasonService;
	@Autowired private EpisodeService episodeService;

	@Override
	public void run(String... args) {
		generateGenres ();
		generateMovies ();
		generateSeries ();
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
		generateGenre ("Sci-Fi");
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
		logger.info("locate movie : " + title);
		Movie movie = movieService.findByTitle(title);

		if (movie == null) {
			logger.info(" -> movie not found, creating");

			movie = new Movie ();

			movie.setTitle(title);
			movie.setDescription(desc);
			movie.setReleasedYear(year);

			movieService.create(movie);
			logger.info(" -> created.");

			for (Genre genre : genres)
				movie.getGenres().add(genre);

			movieService.update(movie);
		}
	}

	private void generateSeries () {
		generateSerie (
			"Heros",
			"Common people discover that they have super powers. Their lives intertwine as a devastating event must be prevented.",
			2006, 4, 20, false,
			genreService.findByName("Drama"),
			genreService.findByName("Fantasy"),
			genreService.findByName("Sci-Fi")
		);
		generateSerie (
			"Supernatural",
			"Two brothers follow their father's footsteps as hunters, fighting evil supernatural beings of many kinds, including monsters, demons and gods that roam the earth.",
			2005, 16, 24, false,
			genreService.findByName("Drama"),
			genreService.findByName("Fantasy"),
			genreService.findByName("Horror")
		);
	}

	private void generateSerie (String title, String descr, int startingYear, int seasons, int episodes, boolean ongoing, Genre... genres) {
		Serie serie = serieService.findByTitle(title);

		if (serie == null) {
			serie = new Serie ();
			serie.setTitle(title);
			serie.setDescription(descr);
			serie.setOngoing(ongoing);
			serieService.create(serie);

			for (Genre genre : genres)
				serie.getGenres().add(genre);

			serieService.update(serie);
		}

		for (int i = 0; i < seasons; i += 1) {
			generateSeason (serie, i+1, startingYear + i, episodes);
		}
	}

	private void generateSeason (Serie serie, int seasonNo, int releasedYear, int episodes) {
		Season season = new Season (serie);

		season.setReleasedYear(releasedYear);
		season.setDesc("Season " + seasonNo);

		seasonService.create(season);
		logger.info("Generated a new season for : " + serie.getTitle());

		for (int i = 1; i <= episodes; i += 1) {
			generateEpisode (season, i);
		}
	}

	private void generateEpisode (Season season, int episodeNo) {
		Episode episode = new Episode (season);
		Random  random  = new Random(System.currentTimeMillis());

		episode.setOrder(episodeNo);
		episode.setName("Episode " + episodeNo);
		episode.setDesc("Something something");
		episode.setDuration(random.nextInt(50) + 20);

		episodeService.create(episode);
		logger.info("Generated a new episode for : " + episode.getName());
	}
}



