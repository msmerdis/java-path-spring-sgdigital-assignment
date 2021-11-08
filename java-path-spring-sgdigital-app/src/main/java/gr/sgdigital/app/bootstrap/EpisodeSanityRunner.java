package gr.sgdigital.app.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import gr.sgdigital.app.bootstrap.SeasonSanityRunner.SeasonKey;
import gr.sgdigital.common.base.BaseComponent;
import gr.sgdigital.common.transfer.ApiStatus;
import gr.sgdigital.movies.service.EpisodeService;
import gr.sgdigital.movies.transfer.EpisodeCreateDTO;
import gr.sgdigital.movies.transfer.EpisodeDetailViewDTO;
import gr.sgdigital.movies.transfer.EpisodeUpdateDTO;

@Component
@Profile("sanity")
public class EpisodeSanityRunner extends BaseComponent implements CommandLineRunner {

	@Autowired private SeasonSanityRunner seasonSanityRunner;
	@Autowired private EpisodeService episodeService;

	public class EpisodeKey {
		public SeasonKey seasonId;
		public Long episodeId;

		public EpisodeKey (SeasonKey seasonId, Long episodeId) {
			this.seasonId  = seasonId;
			this.episodeId = episodeId;
		}
	}

	@Override
	public void run(String... args) throws Exception {
		// create a dummy episode
		EpisodeKey id = createEpisode ("ToUpdate", "ToUpdateDesc", 1, 2400);

		// verify the correct name is stored
		checkEpisode (id, "ToUpdate", "ToUpdateDesc", 1, 2400, "Episode create did not store the correct info");

		// update the episode
		updateEpisode (id, "ToDelete", "ToDeleteDesc", 2, 2500);

		// verify the correct name is stored
		checkEpisode (id, "ToDelete", "ToDeleteDesc", 2, 2500, "Episode update did not store the correct info");

		// delete the episode
		deleteEpisode(id);
	}

	public void deleteEpisode (EpisodeKey id) throws Exception {
		episodeService.delete(id.episodeId);

		// verify the episode is gone
		if (episodeService.exists(id.episodeId)) {
			throw new Exception ("Episode delete did not actually delete the episode");
		}

		// test that season is still here and then delete it as well
		seasonSanityRunner.checkSeason (id.seasonId, "ToCreateEpisodes", "ToCreateEpisodesDesc", 1, 2000, "Episode deletion deleted season as well");
		seasonSanityRunner.deleteSeason(id.seasonId);
	}

	public EpisodeKey createEpisode (String episode, String descr, int order, int duration) throws ApiStatus, Exception {
		// create a season to create episode to
		SeasonKey seasonId = seasonSanityRunner.createSeason("ToCreateEpisodes", "ToCreateEpisodesDesc", 1, 2000);

		EpisodeCreateDTO episodeDTO = new EpisodeCreateDTO();
		episodeDTO.setSeasonId(seasonId.seasonId);
		episodeDTO.setEpisodeName(episode);
		episodeDTO.setEpisodeDesc(descr);
		episodeDTO.setEpisodeOrder(order);
		episodeDTO.setDuration(duration);
		EpisodeDetailViewDTO view = episodeService.create(episodeDTO);

		return new EpisodeKey (seasonId, view.getEpisodeId());
	}

	public void checkEpisode (EpisodeKey id, String episode, String descr, int order, int duration, String error) throws ApiStatus, Exception {
		EpisodeDetailViewDTO theEpisode = episodeService.find(id.episodeId);

		if (theEpisode.getEpisodeId().longValue() != id.episodeId) {
			throw new Exception (error);
		}
		if (!theEpisode.getEpisodeName().equals(episode)) {
			throw new Exception (error);
		}
		if (!theEpisode.getEpisodeDesc().equals(descr)) {
			throw new Exception (error);
		}
		if (theEpisode.getDuration() != duration) {
			throw new Exception (error);
		}
		if (theEpisode.getSeasonId().compareTo(id.seasonId.seasonId) != 0) {
			throw new Exception (error);
		}
	}

	private void updateEpisode (EpisodeKey id, String episode, String descr, int order, int duration) throws ApiStatus, Exception {
		EpisodeUpdateDTO episodeDTO = new EpisodeUpdateDTO();
		episodeDTO.setId(id.episodeId);
		episodeDTO.setEpisodeName(episode);
		episodeDTO.setEpisodeDesc(descr);
		episodeDTO.setEpisodeOrder(order);
		episodeDTO.setDuration(duration);
		episodeService.update(episodeDTO);
	}
}



