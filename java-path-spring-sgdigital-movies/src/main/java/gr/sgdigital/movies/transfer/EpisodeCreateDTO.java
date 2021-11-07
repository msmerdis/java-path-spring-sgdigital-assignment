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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((desc == null) ? 0 : desc.hashCode());
		result = prime * result + duration;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + order;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EpisodeCreateDTO other = (EpisodeCreateDTO) obj;
		if (desc == null) {
			if (other.desc != null)
				return false;
		} else if (!desc.equals(other.desc))
			return false;
		if (duration != other.duration)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (order != other.order)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EpisodeCreateDTO [name=" + name + ", desc=" + desc + ", order=" + order + ", duration=" + duration
				+ "]";
	}

}



