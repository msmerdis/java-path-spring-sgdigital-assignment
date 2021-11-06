package gr.sgdigital.app.bootstrap;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import gr.sgdigital.common.base.BaseComponent;
import gr.sgdigital.movies.domain.Season;
import gr.sgdigital.movies.domain.Serie;
import gr.sgdigital.movies.domain.TitleType;
import gr.sgdigital.movies.service.EpisodeService;
import gr.sgdigital.movies.service.GenreService;
import gr.sgdigital.movies.service.MovieService;
import gr.sgdigital.movies.service.SeasonService;
import gr.sgdigital.movies.service.SerieService;
import gr.sgdigital.movies.service.TitleService;

@Component
public class GenerateContentRunner extends BaseComponent implements CommandLineRunner {

	@Autowired private GenreService genreService;
	@Autowired private TitleService titleService;
	@Autowired private MovieService movieService;
	@Autowired private SerieService serieService;
	@Autowired private SeasonService seasonService;
	@Autowired private EpisodeService episodeService;

	Random random = new Random(System.currentTimeMillis());

	@Override
	public void run(String... args) {
		generateGenres ();
		generateMovies (10);
		generateSeries (10, 1, 10, 10, 24);
	}

	private void generateGenres () {
		genreService.loadOrCreate ("Action");
		genreService.loadOrCreate ("Adventure");
		genreService.loadOrCreate ("Drama");
		genreService.loadOrCreate ("Comedy");
		genreService.loadOrCreate ("Action");
		genreService.loadOrCreate ("Fantasy");
		genreService.loadOrCreate ("Horror");
		genreService.loadOrCreate ("Romance");
		genreService.loadOrCreate ("Western");
		genreService.loadOrCreate ("Thriller");
		genreService.loadOrCreate ("Mystery");
		genreService.loadOrCreate ("Sci-Fi");
	}

	private void generateMovies (int numMovies) {
		for (int i = 0; i < numMovies; i += 1) {
			int releasedYear = 2000 + random.nextInt(20);

			movieService.loadOrCreate(
				titleService.loadOrCreate(
					"title" + i,
					TitleType.MOVIE,
					"description" + i
				), releasedYear
			);
		}
	}

	private void generateSeries (int numSeries, int minSeasons, int maxSeasons, int minEpisodes, int maxEpisodes) {
		for (int i = 0; i < numSeries; i += 1) {
			Serie serie = serieService.loadOrCreate(
				titleService.loadOrCreate(
					"title" + i,
					TitleType.SERIE,
					"description" + i
				), random.nextBoolean()
			);

			int releasedYear = 2000 + random.nextInt(20);
			int numSeasons = minSeasons + random.nextInt(maxSeasons - minSeasons + 1);

			for (int j = 0; j < numSeasons; j += 1) {
				Season season = seasonService.loadOrCreate(serie, j + 1, "Season Name " + j, "Season Description " + j, releasedYear + j);

				int numEpisodes = minEpisodes + random.nextInt(maxEpisodes - minEpisodes + 1);

				for (int k = 0; j < numEpisodes; j += 1) {
					episodeService.loadOrCreate(season, k, "Episode Name " + k, "Episode Description " + k, 1000 + random.nextInt(3000));
				}
			}
		}
	}
}



