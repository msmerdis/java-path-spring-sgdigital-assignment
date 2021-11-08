package gr.sgdigital.movies.transfer;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonProperty;

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

	private long seasonId;

	@NotEmpty(message = "Episodes must have a name")
	private String episodeName;

	@NotNull(message = "Episodes must have a description")
	private String episodeDesc;

	@Positive(message = "Episodes must have a positive order number")
	private int episodeOrder;

	@JsonProperty(value = "durationInSeconds")
	@Positive(message = "The duration of an episode must be a positive number.")
	private int duration;

	@Override
	public void updateEntity(Episode episode) {
		episode.setName(episodeName);
		episode.setDesc(episodeDesc);
		episode.setDuration(duration);
		episode.setOrder(episodeOrder);
	}
}



