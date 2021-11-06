package gr.sgdigital.movies.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import gr.sgdigital.common.domain.BaseEntity;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(
	name = "tseason",
	indexes = {
		@Index(columnList = "id"),
		@Index(columnList = "id, orderNo"),
		@Index(columnList = "id, name")
	}
)
@SequenceGenerator(name = "idGenerator", sequenceName = "SEASON_SEQ", initialValue = 300000, allocationSize = 1)
public class Season extends BaseEntity<Long> {
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "orderNo", nullable = false)
	private int order;

	@Column(length = 128, nullable = false)
	private String name;

	@Column(length = 512, nullable = true)
	private String desc;

	@Column
	private int releasedYear;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "serieId", referencedColumnName = "Id")
	private Serie serie;

	public Season () {
		super ();
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getReleasedYear() {
		return releasedYear;
	}

	public void setReleasedYear(int releasedYear) {
		this.releasedYear = releasedYear;
	}

	public Serie getSerie() {
		return serie;
	}

	public void setSerie(Serie serie) {
		this.serie = serie;
	}
}



