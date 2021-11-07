package gr.sgdigital.app.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import gr.sgdigital.common.base.BaseComponent;
import gr.sgdigital.common.transfer.ApiStatus;
import gr.sgdigital.movies.service.GenreService;
import gr.sgdigital.movies.transfer.GenreCreateDTO;
import gr.sgdigital.movies.transfer.GenreDetailViewDTO;
import gr.sgdigital.movies.transfer.GenreUpdateDTO;

@Component
public class GenreSanityRunner extends BaseComponent implements CommandLineRunner {

	@Autowired private GenreService genreService;

	@Override
	public void run(String... args) throws Exception {
		// create a dummy genre
		int id = createGenre ("ToUpdate");

		// verify the correct name is stored
		checkGenre (id, "ToUpdate", "Genre creation did not store the correct name");

		// update the genre
		updateGenre (id, "ToDelete");

		// verify the correct name is stored
		checkGenre (id, "ToDelete", "Genre update did not store the correct name");

		// delete the genre
		genreService.delete(id);

		// verify the genre is gone
		if (genreService.exists(id)) {
			throw new Exception ("Genre delete did not actually delete the genre");
		}
	}

	private int createGenre (String genre) throws ApiStatus, Exception {
		GenreCreateDTO genreDTO = new GenreCreateDTO();
		genreDTO.setName(genre);
		GenreDetailViewDTO view = genreService.create(genreDTO);
		return view.getGenreId();
	}

	private void checkGenre (int id, String genre, String error) throws ApiStatus, Exception {
		if (!genreService.find(id).getGenreName().equals(genre)) {
			throw new Exception (error);
		}
	}


	private void updateGenre (int id, String genre) throws ApiStatus, Exception {
		GenreUpdateDTO genreDTO = new GenreUpdateDTO();
		genreDTO.setId(id);
		genreDTO.setName(genre);
		genreService.update(genreDTO);
	}
}



