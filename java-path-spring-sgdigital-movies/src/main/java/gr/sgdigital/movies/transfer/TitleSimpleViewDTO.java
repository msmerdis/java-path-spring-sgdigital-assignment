package gr.sgdigital.movies.transfer;

import java.util.Set;
import java.util.stream.Collectors;

import gr.sgdigital.common.transfer.BaseResponseDTO;
import gr.sgdigital.movies.domain.Genre;
import gr.sgdigital.movies.domain.Title;

public class TitleSimpleViewDTO extends BaseResponseDTO<Title> {
	private static final long serialVersionUID = 1L;

	private String name;
	private String desc;
	private Set<String> genres;

	@Override
	public void updateFromEntity(Title title) {
		name = title.getTitle();
		desc = title.getDescription();

		genres = title.getGenres().stream().map(Genre::getName).collect(Collectors.toSet());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Set<String> getGenres() {
		return genres;
	}

	public void setGenres(Set<String> genres) {
		this.genres = genres;
	}

}



