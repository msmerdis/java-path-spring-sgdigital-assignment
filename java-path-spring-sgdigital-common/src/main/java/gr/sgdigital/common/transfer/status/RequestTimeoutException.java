package gr.sgdigital.common.transfer.status;

import org.springframework.http.HttpStatus;

import gr.sgdigital.common.transfer.ApiStatus;

public class RequestTimeoutException extends ApiStatus {
	private static final long serialVersionUID = 1L;

	public RequestTimeoutException(String desc) {
		super(HttpStatus.REQUEST_TIMEOUT, desc);
	}
}



