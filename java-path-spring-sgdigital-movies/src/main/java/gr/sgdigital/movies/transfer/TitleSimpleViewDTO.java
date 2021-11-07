package gr.sgdigital.movies.transfer;

import java.util.Set;
import java.util.stream.Collectors;

import gr.sgdigital.common.transfer.BaseResponseDTO;
import gr.sgdigital.movies.domain.Genre;
import gr.sgdigital.movies.domain.Title;

public class TitleSimpleViewDTO extends BaseResponseDTO<Title> {
	private static final long serialVersionUID = 1L;

	private Long   titleId;
	private String titleName;
	private String titleDesc;
	private Set<String> genres;

	@Override
	public void updateFromEntity(Title title) {
		titleId   = title.getId();
		titleName = title.getTitle();
		titleDesc = title.getDescription();
		genres    = title.getGenres().stream().map(Genre::getName).collect(Collectors.toSet());
	}

	public Long getTitleId() {
		return titleId;
	}

	public void setTitleId(Long titleId) {
		this.titleId = titleId;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((titleId == null) ? 0 : titleId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TitleSimpleViewDTO other = (TitleSimpleViewDTO) obj;
		if (titleId == null) {
			if (other.titleId != null)
				return false;
		} else if (!titleId.equals(other.titleId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TitleSimpleViewDTO [titleId=" + titleId + ", titleName=" + titleName + ", titleDesc=" + titleDesc
				+ ", genres=" + genres + "]";
	}

}



