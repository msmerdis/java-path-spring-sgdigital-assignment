package gr.sgdigital.movies.repository;

import gr.sgdigital.common.repository.AbstractRepository;
import gr.sgdigital.movies.domain.Title;
import gr.sgdigital.movies.transfer.TitleCreateDTO;
import gr.sgdigital.movies.transfer.TitleDetailViewDTO;
import gr.sgdigital.movies.transfer.TitleSimpleViewDTO;
import gr.sgdigital.movies.transfer.TitleUpdateDTO;

public interface TitleRepository extends AbstractRepository<Long, Title, TitleCreateDTO, TitleUpdateDTO, TitleSimpleViewDTO, TitleDetailViewDTO> {
}



