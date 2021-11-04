package gr.sgdigital.common.transfer;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class ApiResponse<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonInclude(Include.NON_EMPTY)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	private LocalDateTime timestamp = LocalDateTime.now();

	@JsonInclude(Include.NON_EMPTY)
	final public T data;

	@JsonInclude(Include.ALWAYS)
	final public ApiStatus status;

	public ApiResponse (ApiStatus status) {
		this (status, null);
	}

	public ApiResponse (ApiStatus status, T data) {
		this.status    = status;
		this.data      = data;
		this.timestamp = LocalDateTime.now();
	}

	public ApiResponse (ApiStatus status, T data, LocalDateTime timestamp) {
		this.status    = status;
		this.data      = data;
		this.timestamp = timestamp;
	}
}



