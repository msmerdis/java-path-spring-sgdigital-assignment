package gr.sgdigital.movies.transfer;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import gr.sgdigital.common.transfer.BaseCreateDTO;
import gr.sgdigital.movies.domain.Episode;

public class EpisodeCreateDTO implements BaseCreateDTO<Episode> {
	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Episodes must have a name")
	private String name;

	@NotNull(message = "Episodes must have a description")
	private String desc;

	@Positive(message = "Episodes must have a positive order number")
	private int order;

	@Positive(message = "The duration of an episode must be a positive number.")
	private int duration;

	public void updateEntity(Episode episode) {
		episode.setName(name);
		episode.setDesc(desc);
		episode.setDuration(duration);
		episode.setOrder(order);
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



