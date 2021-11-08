package gr.sgdigital.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import gr.sgdigital.common.domain.AbstractEntity;
import gr.sgdigital.common.transfer.AbstractResponseDTO;
import gr.sgdigital.common.transfer.AbstractCreateDTO;
import gr.sgdigital.common.transfer.AbstractUpdateDTO;

@NoRepositoryBean
public interface AbstractRepository<
	Key,
	Entity extends AbstractEntity<Key, Entity, SimpleDTO, DetailDTO>,
	CreateDTO extends AbstractCreateDTO<Entity>,
	UpdateDTO extends AbstractUpdateDTO<Entity, Key>,
	SimpleDTO extends AbstractResponseDTO<Entity>,
	DetailDTO extends AbstractResponseDTO<Entity>
> extends JpaRepository<Entity, Key> {
}



