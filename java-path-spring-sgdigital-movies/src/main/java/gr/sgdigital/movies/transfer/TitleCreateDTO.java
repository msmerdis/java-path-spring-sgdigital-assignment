package gr.sgdigital.movies.transfer;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotEmpty;

import gr.sgdigital.common.transfer.BaseCreateDTO;
import gr.sgdigital.movies.domain.Title;

public class TitleCreateDTO implements BaseCreateDTO<Title> {
	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Title must have a name.")
	private String titleName;

	@NotEmpty(message = "Title must have a description.")
	private String titleDesc;

	private Set<String> genres = new HashSet<String>();

	@Override
	public void updateEntity(Title title) {
		title.setTitle(titleName);
		title.setDescription(titleDesc);
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

	public Set<String> getGenres() {
		return genres;
	}

	public void setGenres(Set<String> genres) {
		this.genres = genres;
	}

}



