package gr.sgdigital.movies.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import gr.sgdigital.common.domain.BaseEntity;
import gr.sgdigital.movies.transfer.MovieDetailViewDTO;
import gr.sgdigital.movies.transfer.MovieSimpleViewDTO;

@Entity
@Table(name = "tmovie", indexes = {@Index(columnList = "id")})
@SequenceGenerator(name = "idGenerator", sequenceName = "MOVIE_SEQ", initialValue = 100000, allocationSize = 1)
public class Movie extends BaseEntity <Long, Movie, MovieSimpleViewDTO, MovieDetailViewDTO> {
	@Column
	private int releasedYear;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "titleId", referencedColumnName = "Id")
	private Title title;

	public Movie () {
		super (MovieSimpleViewDTO.class, MovieDetailViewDTO.class);
	}

	public int getReleasedYear() {
		return releasedYear;
	}

	public void setReleasedYear(int releasedYear) {
		this.releasedYear = releasedYear;
	}

	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
	}
}



