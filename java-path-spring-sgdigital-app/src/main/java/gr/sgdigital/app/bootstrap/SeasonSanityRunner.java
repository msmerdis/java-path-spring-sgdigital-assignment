package gr.sgdigital.app.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import gr.sgdigital.common.base.BaseComponent;
import gr.sgdigital.common.transfer.ApiStatus;
import gr.sgdigital.movies.service.SeasonService;
import gr.sgdigital.movies.transfer.SeasonCreateDTO;
import gr.sgdigital.movies.transfer.SeasonDetailViewDTO;
import gr.sgdigital.movies.transfer.SeasonUpdateDTO;

@Component
@Profile("sanity")
public class SeasonSanityRunner extends BaseComponent implements CommandLineRunner {

	@Autowired private SerieSanityRunner serieSanityRunner;
	@Autowired private SeasonService seasonService;

	@Override
	public void run(String... args) throws Exception {
		// create a serie to create season to
		long serieId = serieSanityRunner.createSerie("ToCreateSeasons", "ToUpdateDesc", true, "Action", "Comedy");

		// create a dummy season
		long id = createSeason (serieId, "ToUpdate", "ToUpdatDesc", 1, 1987);

		// verify the correct name is stored
		checkSeason (id, "ToUpdate", "ToUpdatDesc", 1, 1987, serieId, "Season create did not store the correct info");

		// update the season
		updateSeason (id, "ToDelete", "ToDeleteDesc", 2, 2012);

		// verify the correct name is stored
		checkSeason (id, "ToDelete", "ToDeleteDesc", 2, 2012, serieId, "Season create did not store the correct info");

		// delete the season
		seasonService.delete(id);

		// verify the season is gone
		if (seasonService.exists(id)) {
			throw new Exception ("Season delete did not actually delete the season");
		}

		// test that serie is still here and then delete it as well
		serieSanityRunner.checkSerie (serieId, "Season deletion deleted serie as well", "ToCreateSeasons", "ToUpdateDesc", true, "Action", "Comedy");
		serieSanityRunner.deleteSerie(serieId);
	}

	private long createSeason (long seriesId, String season, String descr, int order, int year) throws ApiStatus, Exception {
		SeasonCreateDTO seasonDTO = new SeasonCreateDTO();
		seasonDTO.setSeriesId(seriesId);
		seasonDTO.setSeasonName(season);
		seasonDTO.setSeasonDesc(descr);
		seasonDTO.setSeasonOrder(order);
		seasonDTO.setReleasedYear(year);
		SeasonDetailViewDTO view = seasonService.create(seasonDTO);

		return view.getSeasonId();
	}

	private void checkSeason (long id, String season, String descr, int order, int year, long serieId, String error) throws ApiStatus, Exception {
		SeasonDetailViewDTO theSeason = seasonService.find(id);

		if (theSeason.getSeasonId().longValue() != id) {
			throw new Exception (error + "(1)");
		}
		if (!theSeason.getSeasonName().equals(season)) {
			throw new Exception (error + "(2) " + theSeason.getSeasonName() + " vs " + season);
		}
		if (!theSeason.getSeasonDesc().equals(descr)) {
			throw new Exception (error + "(3)");
		}
		if (theSeason.getReleased() != year) {
			throw new Exception (error + "(4)");
		}
		if (theSeason.getReleased() != year) {
			throw new Exception (error + "(5)");
		}
		if (theSeason.getSerieId() != serieId) {
			throw new Exception (error + "(6)");
		}
	}

	private void updateSeason (long id, String season, String descr, int order, int year) throws ApiStatus, Exception {
		SeasonUpdateDTO seasonDTO = new SeasonUpdateDTO();
		seasonDTO.setId(id);
		seasonDTO.setSeasonName(season);
		seasonDTO.setSeasonDesc(descr);
		seasonDTO.setSeasonOrder(order);
		seasonDTO.setReleasedYear(year);
		seasonService.update(seasonDTO);
	}
}



