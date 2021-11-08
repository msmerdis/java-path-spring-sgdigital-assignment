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

	public class SeasonKey {
		public Long serieId;
		public Long seasonId;

		public SeasonKey (Long serieId, Long seasonId) {
			this.serieId  = serieId;
			this.seasonId = seasonId;
		}
	}

	@Override
	public void run(String... args) throws Exception {
		// create a dummy season
		SeasonKey id = createSeason ("ToUpdate", "ToUpdatDesc", 1, 1987);

		// verify the correct name is stored
		checkSeason (id, "ToUpdate", "ToUpdatDesc", 1, 1987, "Season create did not store the correct info");

		// update the season
		updateSeason (id, "ToDelete", "ToDeleteDesc", 2, 2012);

		// verify the correct name is stored
		checkSeason (id, "ToDelete", "ToDeleteDesc", 2, 2012, "Season create did not store the correct info");

		// delete the season
		deleteSeason(id);
	}

	public void deleteSeason (SeasonKey id) throws Exception {
		seasonService.delete(id.seasonId);

		// verify the season is gone
		if (seasonService.exists(id.seasonId)) {
			throw new Exception ("Season delete did not actually delete the season");
		}

		// test that serie is still here and then delete it as well
		serieSanityRunner.checkSerie (id.serieId, "Season deletion deleted serie as well", "ToCreateSeasons", "ToUpdateDesc", true, "Action", "Comedy");
		serieSanityRunner.deleteSerie(id.serieId);
	}

	public SeasonKey createSeason (String season, String descr, int order, int year) throws ApiStatus, Exception {
		// create a serie to create season to
		long serieId = serieSanityRunner.createSerie("ToCreateSeasons", "ToUpdateDesc", true, "Action", "Comedy");

		SeasonCreateDTO seasonDTO = new SeasonCreateDTO();
		seasonDTO.setSeriesId(serieId);
		seasonDTO.setSeasonName(season);
		seasonDTO.setSeasonDesc(descr);
		seasonDTO.setSeasonOrder(order);
		seasonDTO.setReleasedYear(year);
		SeasonDetailViewDTO view = seasonService.create(seasonDTO);

		return new SeasonKey (serieId, view.getSeasonId());
	}

	public void checkSeason (SeasonKey id, String season, String descr, int order, int year, String error) throws ApiStatus, Exception {
		SeasonDetailViewDTO theSeason = seasonService.find(id.seasonId);

		if (theSeason.getSeasonId().longValue() != id.seasonId) {
			throw new Exception (error);
		}
		if (!theSeason.getSeasonName().equals(season)) {
			throw new Exception (error);
		}
		if (!theSeason.getSeasonDesc().equals(descr)) {
			throw new Exception (error);
		}
		if (theSeason.getReleased() != year) {
			throw new Exception (error);
		}
		if (theSeason.getReleased() != year) {
			throw new Exception (error);
		}
		if (theSeason.getSerieId().compareTo(id.serieId) != 0) {
			throw new Exception (error);
		}
	}

	private void updateSeason (SeasonKey id, String season, String descr, int order, int year) throws ApiStatus, Exception {
		SeasonUpdateDTO seasonDTO = new SeasonUpdateDTO();
		seasonDTO.setId(id.seasonId);
		seasonDTO.setSeasonName(season);
		seasonDTO.setSeasonDesc(descr);
		seasonDTO.setSeasonOrder(order);
		seasonDTO.setReleasedYear(year);
		seasonService.update(seasonDTO);
	}
}



