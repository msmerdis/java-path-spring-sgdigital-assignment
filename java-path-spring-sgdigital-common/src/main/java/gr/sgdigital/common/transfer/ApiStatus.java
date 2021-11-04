package gr.sgdigital.common.transfer;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
@JsonAutoDetect(
	fieldVisibility = Visibility.NONE,
	setterVisibility = Visibility.NONE,
	getterVisibility = Visibility.NONE,
	isGetterVisibility = Visibility.NONE,
	creatorVisibility = Visibility.NONE
)
public class ApiStatus extends Exception implements Serializable {
	private static final long serialVersionUID = 1L;

	public final HttpStatus status;

	@JsonProperty public final int    code;
	@JsonProperty public final String text;
	@JsonProperty public final String desc;

	public ApiStatus (HttpStatus status, String desc) {
		this.status = status;

		this.code = status.value();
		this.text = status.name();
		this.desc = desc;
	}
}



