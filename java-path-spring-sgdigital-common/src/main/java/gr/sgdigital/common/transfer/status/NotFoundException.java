package gr.sgdigital.common.transfer.status;

import org.springframework.http.HttpStatus;

import gr.sgdigital.common.transfer.ApiStatus;

public class NotFoundException extends ApiStatus {
	private static final long serialVersionUID = 1L;

	public NotFoundException(String desc) {
		super(HttpStatus.NOT_FOUND, desc);
	}
}



