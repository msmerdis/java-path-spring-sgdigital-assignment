package gr.sgdigital.movies.transfer;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class MovieDetailViewDTO extends MovieSimpleViewDTO {
	private static final long serialVersionUID = 1L;
}



