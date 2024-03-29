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
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import com.fasterxml.jackson.annotation.JsonIgnore;

import gr.sgdigital.common.domain.BaseEntity;
import gr.sgdigital.movies.transfer.SeasonDetailViewDTO;
import gr.sgdigital.movies.transfer.SeasonSimpleViewDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(
	name = "tseason",
	indexes = {
		@Index(columnList = "id", unique = true),
		@Index(columnList = "serieId, orderNo", unique = true),
		@Index(columnList = "serieId, name"   , unique = true)
	}
)
@SequenceGenerator(name = "idGenerator", sequenceName = "SEASON_SEQ", initialValue = 300000, allocationSize = 1)
@Indexed
public class Season extends BaseEntity <Long, Season, SeasonSimpleViewDTO, SeasonDetailViewDTO> {

	@NotNull
	@Column(name = "orderNo", nullable = false)
	private int order;

	@Field
	@Column(length = 128, nullable = false)
	private String name;

	@Field
	@Column(length = 512, nullable = true)
	private String desc;

	@Column
	private int releasedYear;

	@JsonIgnore
	@Fetch(FetchMode.SELECT)
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "season")
	private Set<Episode> episodes = new HashSet<Episode> ();

	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "serieId", referencedColumnName = "Id")
	private Serie serie;

	public Season () {
		super(SeasonSimpleViewDTO.class, SeasonDetailViewDTO.class);
	}

	// provide a custom detailed view that does not include series
	// this is to be used by SerieDetailViewDTO to avoid
	// adding redundant information to the response
	public SeasonDetailViewDTO seriesView () {
		SeasonDetailViewDTO dto = new SeasonDetailViewDTO ();

		dto.updateWithoutSerieInfo(this);

		return dto;
	}
}



