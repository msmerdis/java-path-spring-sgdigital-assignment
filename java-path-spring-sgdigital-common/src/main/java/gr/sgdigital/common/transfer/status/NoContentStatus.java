package gr.sgdigital.common.transfer.status;

import org.springframework.http.HttpStatus;

import gr.sgdigital.common.transfer.ApiStatus;

public class NoContentStatus extends ApiStatus {
	private static final long serialVersionUID = 1L;

	public NoContentStatus() {
		super(HttpStatus.NO_CONTENT, null);
	}
}



