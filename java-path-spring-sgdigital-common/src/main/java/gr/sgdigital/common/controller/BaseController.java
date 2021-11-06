package gr.sgdigital.common.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import gr.sgdigital.common.base.BaseComponent;
import gr.sgdigital.common.domain.BaseEntity;
import gr.sgdigital.common.service.BaseService;
import gr.sgdigital.common.transfer.ApiResponse;

public abstract class BaseController<E extends BaseEntity<I>, I, S extends BaseService<E, I>> extends BaseComponent {

	final protected S service;

	@Autowired
	public BaseController (S service) {
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
}



