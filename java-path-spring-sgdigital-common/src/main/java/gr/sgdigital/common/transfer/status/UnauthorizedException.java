package gr.sgdigital.common.transfer.status;

import org.springframework.http.HttpStatus;

import gr.sgdigital.common.transfer.ApiStatus;

public class UnauthorizedException extends ApiStatus {
	private static final long serialVersionUID = 1L;

	public UnauthorizedException(String desc) {
		super(HttpStatus.UNAUTHORIZED, desc);
	}
}



