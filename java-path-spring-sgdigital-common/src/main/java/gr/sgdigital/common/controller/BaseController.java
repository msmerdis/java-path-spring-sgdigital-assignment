package gr.sgdigital.common.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import gr.sgdigital.common.base.BaseComponent;
import gr.sgdigital.common.domain.BaseEntity;
import gr.sgdigital.common.service.BaseService;
import gr.sgdigital.common.transfer.ApiResponse;
import gr.sgdigital.common.transfer.status.CreatedStatus;

public abstract class BaseController<E extends BaseEntity<I>, I> extends BaseComponent {

	final protected BaseService<E, I> service;

	public BaseController (BaseService<E, I> service) {
		this.service = service;
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ApiResponse<E> get(@PathVariable("id") final I id) {
		return new ApiResponse<E> (service.find(id));
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ApiResponse<List<E>> findAll() {
		return new ApiResponse<List<E>> (service.findAll());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ApiResponse<BaseEntity<I>> create(@Valid @RequestBody final E entity) {
		return new ApiResponse<BaseEntity<I>> (new CreatedStatus(), service.create(entity));
	}

	@PutMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@Valid @RequestBody final E entity) {
		service.update(entity);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") final I id) {
		service.deleteById(id);
	}

	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@Valid @RequestBody final E entity) {
		if (service.exists(entity)) {
			service.delete(entity);
		}
	}

	protected HttpHeaders getNoCacheHeaders() {
		final HttpHeaders headers = new HttpHeaders();
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");
		return headers;
	}

	protected HttpHeaders getDownloadHeaders(final String filename) {
		final HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=" + filename);
		return headers;
	}
}



