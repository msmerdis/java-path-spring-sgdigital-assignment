package gr.sgdigital.app.bootstrap;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import gr.sgdigital.common.base.BaseComponent;
import gr.sgdigital.common.transfer.ApiStatus;
import gr.sgdigital.movies.base.Formats;
import gr.sgdigital.movies.service.CrewRoleService;
import gr.sgdigital.movies.service.CrewService;
import gr.sgdigital.movies.service.EpisodeService;
import gr.sgdigital.movies.service.GenreService;
import gr.sgdigital.movies.service.MovieService;
import gr.sgdigital.movies.service.SeasonService;
import gr.sgdigital.movies.service.SerieService;
import gr.sgdigital.movies.transfer.CrewCreateDTO;
import gr.sgdigital.movies.transfer.CrewDetailViewDTO;
import gr.sgdigital.movies.transfer.CrewRoleCreateDTO;
import gr.sgdigital.movies.transfer.CrewRoleDetailViewDTO;
import gr.sgdigital.movies.transfer.EpisodeCreateDTO;
import gr.sgdigital.movies.transfer.GenreCreateDTO;
import gr.sgdigital.movies.transfer.GenreDetailViewDTO;
import gr.sgdigital.movies.transfer.MovieCreateDTO;
import gr.sgdigital.movies.transfer.SeasonCreateDTO;
import gr.sgdigital.movies.transfer.SeasonDetailViewDTO;
import gr.sgdigital.movies.transfer.SerieCreateDTO;
import gr.sgdigital.movies.transfer.SerieDetailViewDTO;

@Component
@Profile("test-data")
public class GenerateContentRunner extends BaseComponent implements CommandLineRunner {

	@Autowired private GenreService genreService;
	@Autowired private MovieService movieService;
	@Autowired private SerieService serieService;
	@Autowired private SeasonService seasonService;
	@Autowired private EpisodeService episodeService;
	@Autowired private CrewService crewService;
	@Autowired private CrewRoleService crewRoleService;

	Random random = new Random(System.currentTimeMillis());

	static private List<String> genres    = new LinkedList<String> ();
	static private List<String> crewRoles = new LinkedList<String> ();

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

		Collections.addAll (
			crewRoles,
			"Director",
			"Producer",
			"Writer",
			"Star"
		);
	}

	@Override
	public void run(String... args) throws Exception {
		generateGenres ();
		generateMovies (10);
		generateSeries (10, 1, 10, 10, 24);
		generateCrewRoles ();
		generateCrew (100);
	}

	@Transactional
	private void generateGenres () throws Exception {
		for (String genre : genres) {
			createGenre (genre);
		}
	}

	private int createGenre (String genre) throws ApiStatus, Exception {
		GenreCreateDTO genreDTO = new GenreCreateDTO();
		genreDTO.setName(genre);
		GenreDetailViewDTO view = genreService.create(genreDTO);
		return view.getGenreId();
	}

	private void generateMovies (int numMovies) throws ApiStatus, Exception {
		logger.info("generate movies");
		for (int i = 1; i <= numMovies; i += 1) {
			generateMovie ("Movie title " + i, "Movie Description " + i, 2000 + random.nextInt(20));
		}
	}

	private void generateMovie (String title, String description, int releasedYear) throws ApiStatus, Exception {
		MovieCreateDTO movieDTO = new MovieCreateDTO();

		movieDTO.setMovieName(title);
		movieDTO.setMovieDesc(description);
		movieDTO.setGenres(generateRandomListOfGenres());
		movieDTO.setReleasedYear(releasedYear);

		movieService.create(movieDTO);
	}

	private void generateSeries (int numSeries, int minSeasons, int maxSeasons, int minEpisodes, int maxEpisodes) throws ApiStatus, Exception {
		for (int i = 1; i <= numSeries; i += 1) {

			int releasedYear = 2000 + random.nextInt(20);
			int numSeasons = minSeasons + random.nextInt(maxSeasons - minSeasons + 1);
			int numEpisodes = minEpisodes + random.nextInt(maxEpisodes - minEpisodes + 1);

			generateSerie ("Series title " + i, "Series Description " + i, releasedYear, numSeasons, numEpisodes);
		}
	}

	private void generateSerie (String title, String description, int releasedYear, int numSeasons, int numEpisodes) throws ApiStatus, Exception {
		SerieCreateDTO serieDTO = new SerieCreateDTO();

		serieDTO.setSerieName(title);
		serieDTO.setSerieDesc(description);
		serieDTO.setGenres(generateRandomListOfGenres());
		serieDTO.setOngoing(releasedYear % 2 == 0);

		SerieDetailViewDTO dto = serieService.create(serieDTO);

		for (int i = 1; i <= numSeasons; i += 1) {
			generateSeason (dto.getSerieId(), i, "Season name " + i, "Season Description " + i, releasedYear, numEpisodes);
		}
	}

	private void generateSeason (long serieId, int order, String name, String description, int releasedYear, int numEpisodes) throws ApiStatus, Exception {
		SeasonCreateDTO seasonDTO = new SeasonCreateDTO();

		seasonDTO.setSeriesId(serieId);
		seasonDTO.setSeasonOrder(order);
		seasonDTO.setSeasonName(name);
		seasonDTO.setSeasonDesc(description);
		seasonDTO.setReleasedYear(releasedYear + order - 1);

		SeasonDetailViewDTO dto = seasonService.create(seasonDTO);

		for (int i = 1; i <= numEpisodes; i += 1) {
			generateEpisode (dto.getSeasonId(), i, "Episode name " + i, "Episode Description " + i);
		}
	}

	private void generateEpisode (long seasonId, int order, String name, String description) throws ApiStatus, Exception {
		EpisodeCreateDTO episodeDTO = new EpisodeCreateDTO();

		episodeDTO.setSeasonId(seasonId);
		episodeDTO.setEpisodeOrder(order);
		episodeDTO.setEpisodeName(name);
		episodeDTO.setEpisodeDesc(description);
		episodeDTO.setDuration(20 * 60 + random.nextInt(40 * 60));

		episodeService.create(episodeDTO);
	}

	private Set<String> generateRandomListOfGenres() {
		Set<String> genreList = new HashSet<String>();

		int max = random.nextInt(3);
		Collections.shuffle(genres, random);

		for (int i = 0; i < max; i += 1) {
			genreList.add(genres.get(i));
		}

		return genreList;
	}

	private void generateCrewRoles () throws ApiStatus, Exception {
		for (String crewRole : crewRoles) {
			createCrewRole (crewRole);
		}
	}

	private int createCrewRole (String crewRole) throws ApiStatus, Exception {
		CrewRoleCreateDTO crewRoleDTO = new CrewRoleCreateDTO();
		crewRoleDTO.setName(crewRole);
		CrewRoleDetailViewDTO view = crewRoleService.create(crewRoleDTO);
		return view.getCrewRoleId();
	}

	private void generateCrew (int numCrew) throws ApiStatus, Exception {
		for (int i = 1; i <= numCrew; i += 1) {
			generateCrew ("First name " + i, "Last name " + i, "Middle name " + i, "2000-01-" + (10 + random.nextInt(21)));
		}
	}

	private void generateCrew(String firstname, String lastname, String middlename, String birthDate) throws ApiStatus, Exception {
		CrewCreateDTO crewDTO = new CrewCreateDTO();
  
		crewDTO.setFirstName(firstname);
		crewDTO.setLastName(lastname);
		crewDTO.setMiddleName(middlename);
		crewDTO.setBirthDate(new SimpleDateFormat(Formats.DATE_FORMAT).parse(birthDate));

		CrewDetailViewDTO view = crewService.create(crewDTO);
	}
}



