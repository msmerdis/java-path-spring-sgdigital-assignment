package gr.sgdigital.movies.transfer;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotEmpty;

import gr.sgdigital.common.transfer.BaseUpdateDTO;
import gr.sgdigital.movies.domain.Genre;
import gr.sgdigital.movies.domain.Title;

public class TitleUpdateDTO extends BaseUpdateDTO<Title, Long> {
	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Title must have a name.")
	private String titleName;

	@NotEmpty(message = "Title must have a description.")
	private String titleDesc;

	private Set<Genre> genres = new HashSet<Genre>();

	@Override
	public void updateEntity(Title title) {
		title.setTitle(titleName);
		title.setDescription(titleDesc);
		title.setGenres(genres);
	}

	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

	public String getTitleDesc() {
		return titleDesc;
	}

	public void setTitleDesc(String titleDesc) {
		this.titleDesc = titleDesc;
	}

	public Set<Genre> getGenres() {
		return genres;
	}

	public void setGenres(Set<Genre> genres) {
		this.genres = genres;
	}

}



