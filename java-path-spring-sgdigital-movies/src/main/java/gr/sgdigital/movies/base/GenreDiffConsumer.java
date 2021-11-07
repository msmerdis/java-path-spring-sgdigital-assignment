package gr.sgdigital.movies.base;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

import gr.sgdigital.movies.domain.Genre;
import gr.sgdigital.movies.domain.Title;
import gr.sgdigital.movies.service.GenreService;

public class GenreDiffConsumer implements Consumer<Genre> {
	public Set<String> toCreate;
	public Set<Genre>  toRemove;

	public GenreDiffConsumer (Set<String> genres) {
		toCreate = new HashSet<String>(genres);
		toRemove = new HashSet<Genre>();
	}

	@Override
    public void accept(Genre genre) {
    	if (toCreate.contains(genre.getName())) {
    		toCreate.remove(genre.getName());
    	} else {
    		toRemove.add(genre);
    	}
    }

	public void update (Title title, GenreService genreService) {
		// removing genres
		for (Genre g : toRemove) {
			title.getGenres().remove(g);
		}

		// adding genres
		genreService.mapGenreToTitle(title, toCreate);
	}
}



