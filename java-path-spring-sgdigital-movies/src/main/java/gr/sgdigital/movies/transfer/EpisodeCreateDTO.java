package gr.sgdigital.movies.transfer;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import gr.sgdigital.common.transfer.BaseCreateDTO;
import gr.sgdigital.movies.domain.Episode;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
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

	@Override
	public void updateEntity(Episode episode) {
		episode.setName(name);
		episode.setDesc(desc);
		episode.setDuration(duration);
		episode.setOrder(order);
	}
}



