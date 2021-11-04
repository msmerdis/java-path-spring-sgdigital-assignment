package gr.sgdigital.common.transfer.status;

import org.springframework.http.HttpStatus;

import gr.sgdigital.common.transfer.ApiStatus;

public class MethodNotAllowedException extends ApiStatus {
	private static final long serialVersionUID = 1L;

	public MethodNotAllowedException(String desc) {
		super(HttpStatus.METHOD_NOT_ALLOWED, desc);
	}
}



