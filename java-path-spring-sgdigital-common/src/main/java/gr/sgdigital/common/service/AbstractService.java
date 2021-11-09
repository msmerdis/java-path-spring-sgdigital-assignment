package gr.sgdigital.common.service;

import java.util.List;

import gr.sgdigital.common.domain.AbstractEntity;
import gr.sgdigital.common.transfer.AbstractCreateDTO;
import gr.sgdigital.common.transfer.AbstractResponseDTO;
import gr.sgdigital.common.transfer.AbstractUpdateDTO;
import gr.sgdigital.common.transfer.ApiStatus;

public interface AbstractService<
	Key,
	Entity extends AbstractEntity<Key, Entity, SimpleDTO, DetailDTO>,
	CreateDTO extends AbstractCreateDTO<Entity>,
	UpdateDTO extends AbstractUpdateDTO<Entity, Key>,
	SimpleDTO extends AbstractResponseDTO<Entity>,
	DetailDTO extends AbstractResponseDTO<Entity>
> {
	DetailDTO create(CreateDTO entity) throws ApiStatus, Exception;

	void update(UpdateDTO entity) throws ApiStatus;
	void delete(Key id) throws ApiStatus;

	boolean exists(Key id) throws ApiStatus;
	DetailDTO find(Key id) throws ApiStatus, Exception;

	List<SimpleDTO> findAll() throws ApiStatus, Exception;

	List<DetailDTO> freeTextSearch (String keyword) throws ApiStatus, Exception;
	boolean supportsFreeTextSearch ();
	String entityName ();
}



