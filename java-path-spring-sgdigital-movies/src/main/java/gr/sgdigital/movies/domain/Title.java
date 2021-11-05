package gr.sgdigital.movies.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import gr.sgdigital.common.domain.BaseEntity;

@MappedSuperclass
public class Title extends BaseEntity<Long> {
	private static final long serialVersionUID = 1L;

	@NotNull(message = "Movie must have a title.")
	@Column(length = 128, nullable = false)
	private String title;

	@Column(length = 1024, nullable = true)
	private String description;

	@JsonInclude(Include.NON_NULL)
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
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
}



