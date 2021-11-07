package gr.sgdigital.common.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import gr.sgdigital.common.transfer.ApiStatus;
import gr.sgdigital.common.transfer.BaseCreateDTO;
import gr.sgdigital.common.transfer.BaseResponseDTO;
import gr.sgdigital.common.transfer.BaseUpdateDTO;

public abstract class BaseController<
	Key,
	Entity extends BaseEntity<Key, Entity, SimpleDTO, DetailDTO>,
	CreateDTO extends BaseCreateDTO<Entity>,
	UpdateDTO extends BaseUpdateDTO<Entity, Key>,
	SimpleDTO extends BaseResponseDTO<Entity>,
	DetailDTO extends BaseResponseDTO<Entity>,
	Service extends BaseService<Key, Entity, CreateDTO, UpdateDTO, SimpleDTO, DetailDTO>
> extends BaseComponent {

	final protected Service service;

	@Autowired
	public BaseController (Service service) {
		this.service = service;
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ApiResponse<DetailDTO> get(@PathVariable("id") final Key id) throws ApiStatus {
		return new ApiResponse<DetailDTO> (service.find(id));
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ApiResponse<List<SimpleDTO>> findAll() throws ApiStatus {
		return new ApiResponse<List<SimpleDTO>> (service.findAll());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ApiResponse<DetailDTO> create(@Valid @RequestBody CreateDTO dto) throws Exception {
		return new ApiResponse<DetailDTO> (new ApiStatus(HttpStatus.CREATED, null), service.create(dto));
	}

	@PutMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@Valid @RequestBody final UpdateDTO dto) throws Exception {
		service.update(dto);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") final Key id) throws Exception {
		service.delete(id);
	}
}



