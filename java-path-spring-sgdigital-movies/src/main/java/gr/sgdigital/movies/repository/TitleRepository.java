package gr.sgdigital.movies.repository;

import gr.sgdigital.common.repository.BaseRepository;
import gr.sgdigital.movies.domain.Title;
import gr.sgdigital.movies.transfer.TitleCreateDTO;
import gr.sgdigital.movies.transfer.TitleDetailViewDTO;
import gr.sgdigital.movies.transfer.TitleSimpleViewDTO;
import gr.sgdigital.movies.transfer.TitleUpdateDTO;

public interface TitleRepository extends BaseRepository<Long, Title, TitleCreateDTO, TitleUpdateDTO, TitleSimpleViewDTO, TitleDetailViewDTO> {
}



