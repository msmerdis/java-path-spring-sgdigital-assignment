package gr.sgdigital.movies.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import gr.sgdigital.common.domain.BaseEntity;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "ttitle", indexes = {@Index(columnList = "id")})
@SequenceGenerator(name = "idGenerator", sequenceName = "TITLE_SEQ", initialValue = 100000, allocationSize = 1)
public class Title extends BaseEntity<Long> {
	private static final long serialVersionUID = 1L;

	@NotNull(message = "Movie must have a title.")
	@Column(length = 128, nullable = false)
	private String title;

	@Column(length = 1024, nullable = true)
	private String description;

	@Fetch(FetchMode.SELECT)
	@JoinTable(
		name = "ttitlegenres",
		joinColumns = @JoinColumn(name="titleId"),
		inverseJoinColumns = @JoinColumn(name="genreId")
	)
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Genre> genres = new HashSet<Genre> ();

	@Enumerated(EnumType.ORDINAL)
	private TitleType type;

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

	public TitleType getType() {
		return type;
	}

	public void setType(TitleType type) {
		this.type = type;
	}

	public Set<Genre> getGenres() {
		return genres;
	}

	public void setGenres(Set<Genre> genres) {
		this.genres = genres;
	}
}



