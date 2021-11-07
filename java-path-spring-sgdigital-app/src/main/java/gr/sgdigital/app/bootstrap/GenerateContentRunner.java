package gr.sgdigital.app.bootstrap;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import gr.sgdigital.common.base.BaseComponent;
import gr.sgdigital.common.transfer.ApiStatus;
import gr.sgdigital.movies.service.EpisodeService;
import gr.sgdigital.movies.service.GenreService;
import gr.sgdigital.movies.service.MovieService;
import gr.sgdigital.movies.service.SeasonService;
import gr.sgdigital.movies.service.SerieService;
import gr.sgdigital.movies.service.TitleService;
import gr.sgdigital.movies.transfer.GenreCreateDTO;
import gr.sgdigital.movies.transfer.MovieCreateDTO;
import gr.sgdigital.movies.transfer.TitleCreateDTO;

@Component
public class GenerateContentRunner extends BaseComponent implements CommandLineRunner {

	@Autowired private GenreService genreService;
	@Autowired private MovieService movieService;
	@Autowired private TitleService titleService;
	@Autowired private SerieService serieService;
	@Autowired private SeasonService seasonService;
	@Autowired private EpisodeService episodeService;

	Random random = new Random(System.currentTimeMillis());

	static private List<String> genres = new LinkedList<String> ();

	static {
		Collections.addAll (
			genres,
			"Action",
			"Adventure",
			"Drama",
			"Comedy",
			"Fantasy",
			"Horror",
			"Romance",
			"Western",
			"Thriller",
			"Mystery",
			"Sci-Fi"
		);
	}

	@Override
	public void run(String... args) throws Exception {
		generateGenres ();
	//	generateMovies (10);
	//	generateSeries (10, 1, 10, 10, 24);
	}

	@Transactional
	private void generateGenres () throws Exception {
		for (String genre : genres) {
			GenreCreateDTO genreDTO = new GenreCreateDTO();
			genreDTO.setName(genre);
			genreService.create(genreDTO);
		}
	}

	private void generateMovies (int numMovies) throws ApiStatus, Exception {
		logger.info("generate movies");
		for (int i = 1; i <= numMovies; i += 1) {
			generateMovie ("Movie title " + i, "Movie Description " + i, 2000 + random.nextInt(20));
		}
	}

	private void generateMovie (String title, String description, int releasedYear) throws ApiStatus, Exception {
		MovieCreateDTO movieDTO = new MovieCreateDTO();
		TitleCreateDTO titleDTO = new TitleCreateDTO();

		titleDTO.setTitleName(title);
		titleDTO.setTitleDesc(description);
		titleDTO.setGenres(generateRandomListOfGenres());

		movieDTO.setReleasedYear(releasedYear);
		movieDTO.setTitleDTO(titleDTO);

		movieService.create(movieDTO);
	}

	/*
	private void generateSeries (int numSeries, int minSeasons, int maxSeasons, int minEpisodes, int maxEpisodes) {
		for (int i = 1; i <= numSeries; i += 1) {

			int releasedYear = 2000 + random.nextInt(20);
			int numSeasons = minSeasons + random.nextInt(maxSeasons - minSeasons + 1);
			int numEpisodes = minEpisodes + random.nextInt(maxEpisodes - minEpisodes + 1);

			generateSerie ("Series title " + i, "Series Description " + i, releasedYear, numSeasons, numEpisodes);
		}
	}

	private void generateSerie (String title, String description, int releasedYear, int numSeasons, int numEpisodes) {
		Serie serie = serieService.loadOrCreate(
			titleService.loadOrCreate(
				title,
				TitleType.SERIE,
				description
			), random.nextBoolean()
		);

		for (int j = 1; j <= numSeasons; j += 1) {
			Season season = seasonService.loadOrCreate(serie, j, "Season Name " + j, "Season Description " + j, releasedYear + j);

			for (int k = 1; k <= numEpisodes; k += 1) {
				episodeService.loadOrCreate(season, k, "Episode Name " + k, "Episode Description " + k, 1000 + random.nextInt(3000));
			}
		}

		applyRandomGenres (serie.getTitle().getGenres());

		//serieService.update(serie);
	}*/

	private Set<String> generateRandomListOfGenres() {
		Set<String> genreList = new HashSet<String>();

		int max = random.nextInt(3);
		Collections.shuffle(genres, random);

		for (int i = 0; i < max; i += 1) {
			genreList.add(genres.get(i));
		}

		return genreList;
	}
}



