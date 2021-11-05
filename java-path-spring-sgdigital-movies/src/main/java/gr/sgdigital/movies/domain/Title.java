package gr.sgdigital.movies.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import gr.sgdigital.common.domain.BaseEntity;

@MappedSuperclass
public class Title extends BaseEntity<Long> {
	private static final long serialVersionUID = 1L;

	@NotNull(message = "Movie must have a title.")
	@Column(length = 128, nullable = false)
	private String title;

	@Column(length = 1024, nullable = true)
	private String description;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Genre> genres = new HashSet<Genre> ();

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Genre> getGenres() {
		return genres;
	}

	public void setGenres(Set<Genre> genres) {
		this.genres = genres;
	}
}



