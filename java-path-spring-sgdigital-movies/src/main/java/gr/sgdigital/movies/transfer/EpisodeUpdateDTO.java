package gr.sgdigital.movies.transfer;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonProperty;

import gr.sgdigital.common.transfer.BaseUpdateDTO;
import gr.sgdigital.movies.domain.Episode;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class EpisodeUpdateDTO extends BaseUpdateDTO<Episode, Long> {
	private static final long serialVersionUID = 1L;

	@Positive (message = "Episode order must be a positive number")
	private int episodeOrder;

	@NotEmpty(message = "Episodes must have a name.")
	private String episodeName;

	@NotNull(message = "Episodes must have a description.")
	private String episodeDesc;

	@JsonProperty(value = "durationInSeconds")
	@Positive(message = "The duration of an episode must be a positive number.")
	private int duration;

	@Override
	public void updateEntity (Episode episode) {
		episode.setOrder(episodeOrder);
		episode.setName(episodeName);
		episode.setDesc(episodeDesc);
		episode.setDuration(duration);
	}
}



