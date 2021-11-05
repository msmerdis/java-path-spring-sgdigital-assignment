package gr.sgdigital.movies.domain;

import javax.persistence.AssociationOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "tmovie", indexes = {@Index(columnList = "id")})
@SequenceGenerator(name = "idGenerator", sequenceName = "MOVIE_SEQ", initialValue = 100000, allocationSize = 1)
@AssociationOverride(
	name = "genres",
	joinTable = @JoinTable(
		name = "tmoviegenres",
		indexes = {@Index(columnList = "movieId,genreId")},
		joinColumns = @JoinColumn(name="movieId"),
		inverseJoinColumns = @JoinColumn(name="genreId")
	)
)
public class Movie extends Title {
	private static final long serialVersionUID = 1L;

	@Column
	private int releasedYear;

	public int getReleasedYear() {
		return releasedYear;
	}

	public void setReleasedYear(int releasedYear) {
		this.releasedYear = releasedYear;
	}
}



