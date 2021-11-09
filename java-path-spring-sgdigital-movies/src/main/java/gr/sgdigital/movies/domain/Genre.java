package gr.sgdigital.movies.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import gr.sgdigital.common.domain.BaseEntity;
import gr.sgdigital.movies.transfer.GenreDetailViewDTO;
import gr.sgdigital.movies.transfer.GenreSimpleViewDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(
	name = "tgenre",
	indexes = {
		@Index(columnList =  "id" , unique = true),
		@Index(columnList = "name", unique = true)
	}
)
@SequenceGenerator(name = "idGenerator", sequenceName = "GENRE_SEQ", initialValue = 1, allocationSize = 1)
public class Genre extends BaseEntity <Integer, Genre, GenreSimpleViewDTO, GenreDetailViewDTO> {

	@NotNull(message = "Genre must have a name")
	@Column(length = 32, nullable = false)
	private String name;

	public Genre() {
		super(GenreSimpleViewDTO.class, GenreDetailViewDTO.class);
	}
}



