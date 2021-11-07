package gr.sgdigital.common.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import gr.sgdigital.common.base.BaseComponent;
import gr.sgdigital.common.domain.BaseEntity;
import gr.sgdigital.common.repository.BaseRepository;
import gr.sgdigital.common.transfer.ApiStatus;
import gr.sgdigital.common.transfer.BaseCreateDTO;
import gr.sgdigital.common.transfer.BaseResponseDTO;
import gr.sgdigital.common.transfer.BaseUpdateDTO;
import gr.sgdigital.common.transfer.status.ConflictException;
import gr.sgdigital.common.transfer.status.NotFoundException;

public class BaseServiceImpl<
	Key,
	Entity extends BaseEntity<Key, Entity, SimpleDTO, DetailDTO>,
	CreateDTO extends BaseCreateDTO<Entity>,
	UpdateDTO extends BaseUpdateDTO<Entity, Key>,
	SimpleDTO extends BaseResponseDTO<Entity>,
	DetailDTO extends BaseResponseDTO<Entity>,
	Repository extends BaseRepository<Key, Entity, CreateDTO, UpdateDTO, SimpleDTO, DetailDTO>
> extends BaseComponent implements BaseService<Key, Entity, CreateDTO, UpdateDTO, SimpleDTO, DetailDTO> {

	final protected Repository repository;
	final private Class<Entity> entityClass;

	public BaseServiceImpl (Repository repository, Class<Entity> entityClass) {
		this.repository  = repository;
		this.entityClass = entityClass;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
	public DetailDTO create(CreateDTO dto) throws ApiStatus, Exception {
		Entity entity = entityClass.getDeclaredConstructor().newInstance();

		dto.updateEntity(entity);

		try {
			entity = repository.saveAndFlush(entity);
		} catch (Exception e) {
			throw new ConflictException("Entity already exists");
		}

		return (DetailDTO) entity.detailView();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
	public void update(UpdateDTO dto) throws ApiStatus {
		Optional<Entity> optionalEntity = repository.findById(dto.getId());

		if (optionalEntity.isEmpty()) {
			throw new NotFoundException("Entity not found.");
		}

		Entity entity = optionalEntity.get();

		dto.updateEntity(entity);

		try {
			repository.saveAndFlush(entity);
		} catch (Exception e) {
			throw new ConflictException("Cannot update entity");
		}

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
	public void delete(Key id) throws ApiStatus {
		Optional<Entity> entity = repository.findById(id);

		if (entity.isEmpty()) {
			throw new NotFoundException("Entity not found.");
		}

		repository.deleteById(id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
	public boolean exists(Key id) throws ApiStatus {
		return repository.existsById(id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
	public DetailDTO find(Key id) throws ApiStatus {
		Optional<Entity> entity = repository.findById(id);

		if (entity.isEmpty()) {
			throw new NotFoundException("Entity not found.");
		}

		return (DetailDTO) entity.get().detailView();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
	public List<SimpleDTO> findAll() throws ApiStatus {
		return repository.findAll().stream().map(e -> (SimpleDTO) e.simpleView()).collect(Collectors.toList());
	}
}



