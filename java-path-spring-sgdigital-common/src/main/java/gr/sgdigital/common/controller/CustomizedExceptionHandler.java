package gr.sgdigital.common.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import gr.sgdigital.common.base.AbstractComponent;
import gr.sgdigital.common.transfer.ApiResponse;
import gr.sgdigital.common.transfer.ApiStatus;
import gr.sgdigital.common.transfer.status.MethodNotAllowedException;
import gr.sgdigital.common.transfer.status.NotFoundException;

@RestControllerAdvice
public class CustomizedExceptionHandler extends AbstractComponent {

	/**
	 * Universal handler for all different api statuses
	 */
	@ExceptionHandler(ApiStatus.class)
	public final ResponseEntity<?> handleAllApiErrors(final ApiStatus ex) {
		ApiResponse<String> response = new ApiResponse<String> (ex);

		return new ResponseEntity<ApiResponse<String>> (
			response,
			ex.status
		);
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public final ResponseEntity<?> handleNoHandlerFoundException (final NoHandlerFoundException ex) {
		return handleAllApiErrors (
			new NotFoundException(ex.getMessage())
		);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public final ResponseEntity<?> handleNotSupportedMethodException (final HttpRequestMethodNotSupportedException ex) {
		return handleAllApiErrors (
			new MethodNotAllowedException(ex.getMessage())
		);
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<?> handleInternalServerException (final Exception ex) {
		return handleAllApiErrors (
			new ApiStatus(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage())
		);
	}
}



