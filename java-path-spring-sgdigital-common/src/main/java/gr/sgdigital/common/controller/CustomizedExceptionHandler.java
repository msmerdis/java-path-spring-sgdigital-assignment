package gr.sgdigital.common.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import gr.sgdigital.common.base.BaseComponent;
import gr.sgdigital.common.transfer.ApiResponse;
import gr.sgdigital.common.transfer.ApiStatus;
import gr.sgdigital.common.transfer.status.BadRequestException;
import gr.sgdigital.common.transfer.status.MethodNotAllowedException;
import gr.sgdigital.common.transfer.status.NotFoundException;

@RestControllerAdvice
public class CustomizedExceptionHandler extends BaseComponent {

	/**
	 * Universal handler for all different api statuses
	 */
	@ExceptionHandler(ApiStatus.class)
	public final ResponseEntity<?> handleAllApiErrors(final ApiStatus ex) {
		return generateResponse (ex, null);
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public final ResponseEntity<?> handleNoHandlerFoundException (final NoHandlerFoundException ex) {
		return generateResponse (
			new NotFoundException(ex.getMessage()), null
		);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public final ResponseEntity<?> handleHttpRequestMethodNotSupportedException (final HttpRequestMethodNotSupportedException ex) {
		return generateResponse (
			new MethodNotAllowedException(ex.getMessage()), null
		);
	}

	@ExceptionHandler(MissingRequestHeaderException.class)
	public final ResponseEntity<?> handleMissingRequestHeaderException (final MissingRequestHeaderException ex) {
		return generateResponse (
			new BadRequestException(ex.getMessage()), null
		);
	}

	@ExceptionHandler(value=MethodArgumentNotValidException.class)
	public final ResponseEntity<?> handleException(MethodArgumentNotValidException ex) {
		return generateResponse (
			new BadRequestException ("Validation erros"),
			ex.getBindingResult().getFieldErrors().stream()
				.map(err -> err.getField() + " for value " +  err.getRejectedValue() + " : " + err.getDefaultMessage())
				.distinct()
				.collect(Collectors.toList())
		);
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<?> handleInternalServerException (final Exception ex) {
		return generateResponse (
			new ApiStatus(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()), null
		);
	}

	private ResponseEntity<?> generateResponse (final ApiStatus ex, List<String> info) {
		ApiResponse<String> response = new ApiResponse<String> (ex);

		response.setInfo(info);

		return new ResponseEntity<ApiResponse<String>> (
			response,
			ex.status
		);
	}
}



