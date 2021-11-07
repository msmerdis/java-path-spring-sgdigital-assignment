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
import gr.sgdigital.movies.service.TitleService;
import gr.sgdigital.movies.transfer.TitleCreateDTO;
import gr.sgdigital.movies.transfer.TitleDetailViewDTO;
import gr.sgdigital.movies.transfer.TitleUpdateDTO;

@Component
@Profile("sanity")
public class TitleSanityRunner extends BaseComponent implements CommandLineRunner {

	@Autowired private TitleService titleService;

	@Override
	public void run(String... args) throws Exception {
		// create a dummy title
		long id = createTitle ("ToUpdate", "ToUpdateDesc", "Action", "Comedy");

		// verify the correct name is stored
		checkTitle (id, "Title creation did not store the correct name", "ToUpdate", "ToUpdateDesc", "Action", "Comedy");

		// update the title
		updateTitle (id, "ToAddGenre", "ToAddGenreDesc", "Action", "Comedy");

		// verify the correct name is stored
		checkTitle (id, "Title update did not store the correct value", "ToAddGenre", "ToAddGenreDesc", "Action", "Comedy");

		// add a new genre
		updateTitle (id, "ToDelete", "ToDeleteDesc", "Comedy", "Horror");

		// verify the genre is stored
		checkTitle (id, "Title update did not store the correct value", "ToDelete", "ToDeleteDesc", "Comedy", "Horror");

		// delete the title
		titleService.delete(id);

		// verify the title is gone
		if (titleService.exists(id)) {
			throw new Exception ("Title delete did not actually delete the title");
		}
	}

	private long createTitle (String title, String descr, String... genres) throws ApiStatus, Exception {
		TitleCreateDTO titleDTO = new TitleCreateDTO();
		titleDTO.setTitleName(title);
		titleDTO.setTitleDesc(descr);
		titleDTO.setGenres(Arrays.stream(genres).collect(Collectors.toSet()));
		TitleDetailViewDTO view = titleService.create(titleDTO);

		return view.getTitleId();
	}

	private void checkTitle (long id, String error, String title, String descr, String... genres) throws ApiStatus, Exception {
		TitleDetailViewDTO theTitle = titleService.find(id);
		Set<String> genreSet = Arrays.stream(genres).collect(Collectors.toSet());

		if (theTitle.getTitleId().longValue() != id) {
			throw new Exception (error);
		}
		if (!theTitle.getTitleName().equals(title)) {
			throw new Exception (error);
		}
		if (!theTitle.getTitleDesc().equals(descr)) {
			throw new Exception (error);
		}
		if (!theTitle.getGenres().containsAll(genreSet)) {
			throw new Exception (error);
		}
		if (!genreSet.containsAll(theTitle.getGenres())) {
			throw new Exception (error);
		}
	}

	private void updateTitle (long id, String title, String descr, String... genres) throws ApiStatus, Exception {
		TitleUpdateDTO titleDTO = new TitleUpdateDTO();
		titleDTO.setId(id);
		titleDTO.setTitleName(title);
		titleDTO.setTitleDesc(descr);
		titleDTO.setGenres(Arrays.stream(genres).collect(Collectors.toSet()));
		titleService.update(titleDTO);
	}
}



