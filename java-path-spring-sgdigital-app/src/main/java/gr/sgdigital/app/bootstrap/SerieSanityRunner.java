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
import gr.sgdigital.movies.service.SerieService;
import gr.sgdigital.movies.transfer.SerieCreateDTO;
import gr.sgdigital.movies.transfer.SerieDetailViewDTO;
import gr.sgdigital.movies.transfer.SerieUpdateDTO;

@Component
@Profile("sanity")
public class SerieSanityRunner extends BaseComponent implements CommandLineRunner {

	@Autowired private SerieService serieService;

	@Override
	public void run(String... args) throws Exception {
		// create a dummy serie
		long id = createSerie ("ToUpdate", "ToUpdateDesc", true, "Action", "Comedy");

		// verify the correct name is stored
		checkSerie (id, "Serie creation did not store the correct name", "ToUpdate", "ToUpdateDesc", true, "Action", "Comedy");

		// update the serie
		updateSerie (id, "ToAddGenre", "ToAddGenreDesc", false, "Action", "Comedy");

		// verify the correct name is stored
		checkSerie (id, "Serie update did not store the correct value", "ToAddGenre", "ToAddGenreDesc", false, "Action", "Comedy");

		// add a new genre
		updateSerie (id, "ToDelete", "ToDeleteDesc", false, "Comedy", "Horror");

		// verify the genre is stored
		checkSerie (id, "Serie update did not store the correct value", "ToDelete", "ToDeleteDesc", false, "Comedy", "Horror");

		// delete the serie
		deleteSerie (id);
	}

	public void deleteSerie (long id) throws Exception {
		serieService.delete(id);

		// verify the serie is gone
		if (serieService.exists(id)) {
			throw new Exception ("Serie delete did not actually delete the serie");
		}
	}

	public long createSerie (String serie, String descr, boolean ongoing, String... genres) throws ApiStatus, Exception {
		SerieCreateDTO serieDTO = new SerieCreateDTO();
		serieDTO.setSerieName(serie);
		serieDTO.setSerieDesc(descr);
		serieDTO.setOngoing(ongoing);
		serieDTO.setGenres(Arrays.stream(genres).collect(Collectors.toSet()));
		SerieDetailViewDTO view = serieService.create(serieDTO);
		return view.getSerieId();
	}

	public void checkSerie (long id, String error, String serie, String descr, boolean ongoing, String... genres) throws ApiStatus, Exception {
		SerieDetailViewDTO theSerie = serieService.find(id);
		Set<String> genreSet = Arrays.stream(genres).collect(Collectors.toSet());

		if (theSerie.getSerieId().longValue() != id) {
			throw new Exception (error);
		}
		if (!theSerie.getSerieName().equals(serie)) {
			throw new Exception (error);
		}
		if (!theSerie.getSerieDesc().equals(descr)) {
			throw new Exception (error);
		}
		if (theSerie.getOngoing() != ongoing) {
			throw new Exception (error);
		}
		if (!theSerie.getGenres().containsAll(genreSet)) {
			throw new Exception (error);
		}
		if (!genreSet.containsAll(theSerie.getGenres())) {
			throw new Exception (error);
		}
	}

	private void updateSerie (long id, String serie, String descr, boolean ongoing, String... genres) throws ApiStatus, Exception {
		SerieUpdateDTO serieDTO = new SerieUpdateDTO();
		serieDTO.setId(id);
		serieDTO.setSerieName(serie);
		serieDTO.setSerieDesc(descr);
		serieDTO.setOngoing(ongoing);
		serieDTO.setGenres(Arrays.stream(genres).collect(Collectors.toSet()));
		serieService.update(serieDTO);
	}
}



