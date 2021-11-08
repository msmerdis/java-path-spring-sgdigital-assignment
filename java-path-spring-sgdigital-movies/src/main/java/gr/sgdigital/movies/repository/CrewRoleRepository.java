package gr.sgdigital.movies.repository;

import java.util.Optional;

import gr.sgdigital.common.repository.BaseRepository;
import gr.sgdigital.movies.domain.CrewRole;
import gr.sgdigital.movies.transfer.CrewRoleCreateDTO;
import gr.sgdigital.movies.transfer.CrewRoleDetailViewDTO;
import gr.sgdigital.movies.transfer.CrewRoleSimpleViewDTO;
import gr.sgdigital.movies.transfer.CrewRoleUpdateDTO;

public interface CrewRoleRepository extends BaseRepository<Integer, CrewRole, CrewRoleCreateDTO, CrewRoleUpdateDTO, CrewRoleSimpleViewDTO, CrewRoleDetailViewDTO> {
	Optional<CrewRole> findByName (String name);
}



