package gr.sgdigital.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import gr.sgdigital.common.domain.BaseEntity;
import gr.sgdigital.common.transfer.BaseCreateDTO;
import gr.sgdigital.common.transfer.BaseResponseDTO;
import gr.sgdigital.common.transfer.BaseUpdateDTO;

@NoRepositoryBean
public interface BaseRepository<
	Key,
	Entity extends BaseEntity<Key, Entity, SimpleDTO, DetailDTO>,
	CreateDTO extends BaseCreateDTO<Entity>,
	UpdateDTO extends BaseUpdateDTO<Entity, Key>,
	SimpleDTO extends BaseResponseDTO<Entity>,
	DetailDTO extends BaseResponseDTO<Entity>
> extends JpaRepository<Entity, Key> {
}



