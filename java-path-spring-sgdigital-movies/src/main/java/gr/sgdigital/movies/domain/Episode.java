package gr.sgdigital.movies.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import com.fasterxml.jackson.annotation.JsonIgnore;

import gr.sgdigital.common.domain.BaseEntity;
import gr.sgdigital.movies.transfer.EpisodeDetailViewDTO;
import gr.sgdigital.movies.transfer.EpisodeSimpleViewDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(
	name = "tepisode",
	indexes = {
		@Index(columnList = "id"),
		@Index(columnList = "id, orderNo"),
		@Index(columnList = "id, name")
	}
)
@SequenceGenerator(name = "idGenerator", sequenceName = "EPISODE_SEQ", initialValue = 400000, allocationSize = 1)
@Indexed
public class Episode extends BaseEntity <Long, Episode, EpisodeSimpleViewDTO, EpisodeDetailViewDTO> {

	@Column(name = "orderNo", nullable = false)
	private int order;

	@Field
	@Column(length = 128, nullable = false)
	private String name;

	@Field
	@Column(length = 512, nullable = false)
	private String desc;

	@Column
	private int duration;

	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "seasonId", referencedColumnName = "Id")
	private Season season;

	public Episode() {
		super(EpisodeSimpleViewDTO.class, EpisodeDetailViewDTO.class);
	}
}



