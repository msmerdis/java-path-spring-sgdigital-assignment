package gr.sgdigital.movies.transfer;

import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonProperty;

import gr.sgdigital.common.transfer.BaseCreateDTO;
import gr.sgdigital.movies.domain.Movie;
import gr.sgdigital.movies.domain.Title;

public class MovieCreateDTO implements BaseCreateDTO<Movie> {
	private static final long serialVersionUID = 1L;

	@JsonProperty("title")
	TitleCreateDTO titleDTO;

	@Positive(message = "Movie's released year must be a positive number.")
	private int releasedYear;

	public void updateEntity(Movie movie) {
		Title title = new Title ();

		titleDTO.updateEntity(title);

		movie.setReleasedYear(releasedYear);
		movie.setTitle(title);
	}

	public TitleCreateDTO getTitleDTO() {
		return titleDTO;
	}

	public void setTitleDTO(TitleCreateDTO titleDTO) {
		this.titleDTO = titleDTO;
	}

	public int getReleasedYear() {
		return releasedYear;
	}

	public void setReleasedYear(int releasedYear) {
		this.releasedYear = releasedYear;
	}

}



