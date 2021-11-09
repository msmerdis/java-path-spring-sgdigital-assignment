package gr.sgdigital.movies.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

import gr.sgdigital.common.domain.BaseEntity;
import gr.sgdigital.movies.transfer.SerieDetailViewDTO;
import gr.sgdigital.movies.transfer.SerieSimpleViewDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(
	name = "tserie",
	indexes = {
		@Index(columnList = "id", unique = true)
	}
)
@SequenceGenerator(name = "idGenerator", sequenceName = "SERIE_SEQ", initialValue = 200000, allocationSize = 1)
public class Serie extends BaseEntity <Long, Serie, SerieSimpleViewDTO, SerieDetailViewDTO> {

	@Column
	private Boolean ongoing;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "titleId", referencedColumnName = "Id")
	private Title title;

	@JsonIgnore
	@Fetch(FetchMode.SELECT)
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "serie")
	private Set<Season> seasons = new HashSet<Season> ();

	public Serie () {
		super (SerieSimpleViewDTO.class, SerieDetailViewDTO.class);
	}
}



