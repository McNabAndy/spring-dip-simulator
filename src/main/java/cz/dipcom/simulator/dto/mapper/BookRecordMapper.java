package cz.dipcom.simulator.dto.mapper;

import cz.dipcom.simulator.dto.BookRecordDTO;
import cz.dipcom.simulator.dto.BookRecordResponseDTO;
import cz.dipcom.simulator.entity.BookRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface BookRecordMapper {

    @Mapping(target = "itemResponseDTO", source = "item")
    @Mapping(target = "resourcesResponseDTO", source = "resources")
    @Mapping(target = "segmentsResponseDTO", source = "segments")
    BookRecordResponseDTO toResponseDTO (BookRecord source);

    @Mapping(target = "item", source = "itemDTO")
    @Mapping(target = "resources", source = "resourcesDTO")
    @Mapping(target = "segments", source = "segmentsDTO")
    BookRecord toBookRecord (BookRecordDTO source);




}
