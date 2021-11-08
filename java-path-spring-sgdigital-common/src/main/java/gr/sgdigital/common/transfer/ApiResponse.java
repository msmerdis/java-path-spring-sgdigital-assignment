package gr.sgdigital.common.transfer;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import gr.sgdigital.common.transfer.status.SuccessStatus;

public class ApiResponse<E> implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonInclude(Include.NON_EMPTY)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	private LocalDateTime timestamp = LocalDateTime.now();

	@JsonInclude(Include.NON_NULL)
	final public E data;

	@JsonInclude(Include.ALWAYS)
	final public ApiStatus status;

	@JsonInclude(Include.NON_NULL)
	public List<String> info;

	public ApiResponse (ApiStatus status) {
		this (status, null);
	}

	public ApiResponse (E data) {
		this (new SuccessStatus(), data);
	}

	public ApiResponse (ApiStatus status, E data) {
		this.status    = status;
		this.data      = data;
		this.timestamp = LocalDateTime.now();
	}

	public ApiResponse (ApiStatus status, E data, LocalDateTime timestamp) {
		this.status    = status;
		this.data      = data;
		this.timestamp = timestamp;
	}

	public List<String> getInfo() {
		return info;
	}

	public void setInfo(List<String> info) {
		this.info = info;
	}
}



