package gr.sgdigital.movies.transfer;

import gr.sgdigital.common.transfer.BaseResponseDTO;
import gr.sgdigital.movies.domain.Episode;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class EpisodeSimpleViewDTO extends BaseResponseDTO<Episode> {
	private static final long serialVersionUID = 1L;

	private int    order;
	private Long   episodeId;
	private String episodeName;
	private String episodeDesc;
	private int    duration;

	@Override
	public void updateFromEntity(Episode episode) {
		order       = episode.getOrder();
		episodeId   = episode.getId();
		episodeName = episode.getName();
		episodeDesc = episode.getDesc();
		duration    = episode.getDuration();
	}
}



