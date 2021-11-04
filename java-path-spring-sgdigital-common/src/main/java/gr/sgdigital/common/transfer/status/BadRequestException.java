package gr.sgdigital.common.transfer.status;

import org.springframework.http.HttpStatus;

import gr.sgdigital.common.transfer.ApiStatus;

public class BadRequestException extends ApiStatus {
	private static final long serialVersionUID = 1L;

	public BadRequestException(String desc) {
		super(HttpStatus.BAD_REQUEST, desc);
	}
}



