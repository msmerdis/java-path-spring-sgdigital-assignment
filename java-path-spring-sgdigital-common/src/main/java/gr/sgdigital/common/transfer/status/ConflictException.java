package gr.sgdigital.common.transfer.status;

import org.springframework.http.HttpStatus;

import gr.sgdigital.common.transfer.ApiStatus;

public class ConflictException extends ApiStatus {
	private static final long serialVersionUID = 1L;

	public ConflictException(String desc) {
		super(HttpStatus.CONFLICT, desc);
	}
}



