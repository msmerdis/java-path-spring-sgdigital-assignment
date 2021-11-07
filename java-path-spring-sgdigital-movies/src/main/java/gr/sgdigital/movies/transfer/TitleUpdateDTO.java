package gr.sgdigital.movies.transfer;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotEmpty;

import gr.sgdigital.common.transfer.BaseUpdateDTO;
import gr.sgdigital.movies.domain.Title;

public class TitleUpdateDTO extends BaseUpdateDTO<Title, Long> {
	private static final long serialVersionUID = 1L;

	private Long   titleId;

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
		result = prime * result + ((genres == null) ? 0 : genres.hashCode());
		result = prime * result + ((titleDesc == null) ? 0 : titleDesc.hashCode());
		result = prime * result + ((titleId == null) ? 0 : titleId.hashCode());
		result = prime * result + ((titleName == null) ? 0 : titleName.hashCode());
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
		TitleUpdateDTO other = (TitleUpdateDTO) obj;
		if (genres == null) {
			if (other.genres != null)
				return false;
		} else if (!genres.equals(other.genres))
			return false;
		if (titleDesc == null) {
			if (other.titleDesc != null)
				return false;
		} else if (!titleDesc.equals(other.titleDesc))
			return false;
		if (titleId == null) {
			if (other.titleId != null)
				return false;
		} else if (!titleId.equals(other.titleId))
			return false;
		if (titleName == null) {
			if (other.titleName != null)
				return false;
		} else if (!titleName.equals(other.titleName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TitleUpdateDTO [titleId=" + titleId + ", titleName=" + titleName + ", titleDesc=" + titleDesc
				+ ", genres=" + genres + "]";
	}

}



