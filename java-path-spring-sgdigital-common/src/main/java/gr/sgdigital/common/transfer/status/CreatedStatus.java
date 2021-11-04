package gr.sgdigital.common.transfer.status;

import org.springframework.http.HttpStatus;

import gr.sgdigital.common.transfer.ApiStatus;

public class CreatedStatus extends ApiStatus {
	private static final long serialVersionUID = 1L;

	public CreatedStatus() {
		super(HttpStatus.CREATED, null);
	}
}



