package gr.sgdigital.common.service;

import java.util.List;

import gr.sgdigital.common.domain.BaseEntity;
import gr.sgdigital.common.transfer.ApiStatus;
import gr.sgdigital.common.transfer.BaseCreateDTO;
import gr.sgdigital.common.transfer.BaseResponseDTO;
import gr.sgdigital.common.transfer.BaseUpdateDTO;

public interface BaseService<
	Key,
	Entity extends BaseEntity<Key, Entity, SimpleDTO, DetailDTO>,
	CreateDTO extends BaseCreateDTO<Entity>,
	UpdateDTO extends BaseUpdateDTO<Entity, Key>,
	SimpleDTO extends BaseResponseDTO<Entity>,
	DetailDTO extends BaseResponseDTO<Entity>
> {
	DetailDTO create(CreateDTO entity) throws ApiStatus, Exception;

	void update(UpdateDTO entity) throws ApiStatus;
	void delete(Key id) throws ApiStatus;

	boolean exists(Key id) throws ApiStatus;
	DetailDTO find(Key id) throws ApiStatus;

	List<SimpleDTO> findAll() throws ApiStatus;
}



