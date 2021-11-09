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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;

import gr.sgdigital.common.base.BaseComponent;
import gr.sgdigital.common.domain.AbstractEntity;
import gr.sgdigital.common.service.AbstractService;
import gr.sgdigital.common.transfer.AbstractCreateDTO;
import gr.sgdigital.common.transfer.AbstractResponseDTO;
import gr.sgdigital.common.transfer.AbstractUpdateDTO;
import gr.sgdigital.common.transfer.ApiResponse;
import gr.sgdigital.common.transfer.ApiStatus;
import gr.sgdigital.common.transfer.status.NotImplementedException;

public abstract class AbstractController<
	Key,
	Entity extends AbstractEntity<Key, Entity, SimpleDTO, DetailDTO>,
	CreateDTO extends AbstractCreateDTO<Entity>,
	UpdateDTO extends AbstractUpdateDTO<Entity, Key>,
	SimpleDTO extends AbstractResponseDTO<Entity>,
	DetailDTO extends AbstractResponseDTO<Entity>,
	Service extends AbstractService<Key, Entity, CreateDTO, UpdateDTO, SimpleDTO, DetailDTO>
> extends BaseComponent {

	final protected Service service;

	@Autowired
	public AbstractController (Service service) {
		this.service = service;
	}

	@GetMapping("/search")
	@ResponseStatus(HttpStatus.OK)
	public ApiResponse<List<DetailDTO>> search(@RequestHeader("X-Search-Terms") String keyword) throws Exception {
		return new ApiResponse<List<DetailDTO>> (service.freeTextSearch(keyword));
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ApiResponse<DetailDTO> get(@PathVariable("id") final Key id) throws Exception {
		return new ApiResponse<DetailDTO> (service.find(id));
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ApiResponse<List<SimpleDTO>> findAll() throws Exception {
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



