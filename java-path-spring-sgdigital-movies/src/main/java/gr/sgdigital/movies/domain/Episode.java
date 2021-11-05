package gr.sgdigital.movies.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import gr.sgdigital.common.domain.BaseEntity;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "tepisode", indexes = {@Index(columnList = "id")})
@SequenceGenerator(name = "idGenerator", sequenceName = "EPISODE_SEQ", initialValue = 400000, allocationSize = 1)
public class Episode extends BaseEntity<Long> {
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@JoinColumn(name="seasonId")
	@ManyToOne(fetch = FetchType.LAZY)
	private Season season;

	@NotNull(message = "Episode's name must be present")
	@Column(length = 128, nullable = false)
	private String name;

	@Column(length = 512, nullable = false)
	private String desc;

	@Column(name = "orderNo")
	private int order;

	@Column
	private int duration;

	public Episode () {
		super ();
	}

	public Episode (Season season) {
		super ();
		this.season = season;
	}

	public Season getSeason() {
		return season;
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

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
}



