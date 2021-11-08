package gr.sgdigital.movies.transfer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@JsonInclude(content = Include.NON_NULL)
public class TitleCrewDetailViewDTO extends TitleCrewSimpleViewDTO {
	private static final long serialVersionUID = 1L;
}



