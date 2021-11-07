package gr.sgdigital.movies.transfer;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import gr.sgdigital.common.transfer.BaseUpdateDTO;
import gr.sgdigital.movies.domain.Episode;

public class EpisodeUpdateDTO extends BaseUpdateDTO<Episode, Long> {
	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Episodes must have a name.")
	private String name;

	@NotNull(message = "Episodes must have a description.")
	private String desc;

	@Positive(message = "The duration of an episode must be a positive number.")
	private int duration;

	@Override
	public void updateEntity (Episode entity) {
		entity.setName(name);
		entity.setDesc(desc);
		entity.setDuration(duration);
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

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

}



