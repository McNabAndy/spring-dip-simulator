package cz.dipcom.simulator.dto.mapper;

import cz.dipcom.simulator.dto.BookRecordDTO;
import cz.dipcom.simulator.dto.response.BookRecordResponseDTO;
import cz.dipcom.simulator.entity.BookRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * BookRecordMapper is an interface for mapping between entities and DTOs related to book records.
 * It uses MapStruct to define the mapping between {@link BookRecord} and the corresponding DTOs.
 * The interface defines methods for converting a {@link BookRecord} entity to a {@link BookRecordResponseDTO}
 * and vice versa, using MapStruct annotations to manage the mappings.
 */
@Mapper(componentModel = "spring")
public interface BookRecordMapper {


    /**
     * Maps a {@link BookRecord} entity to a {@link BookRecordResponseDTO}.
     * The mapping includes the conversion of nested objects like `item`, `resources`, and `segments`
     * into their corresponding response DTOs (`itemResponseDTO`, `resourcesResponseDTO`, `segmentsResponseDTO`).
     *
     * @param source the {@link BookRecord} entity to be converted
     * @return the mapped {@link BookRecordResponseDTO}
     */
    @Mapping(target = "itemResponseDTO", source = "item")
    @Mapping(target = "resourcesResponseDTO", source = "resources")
    @Mapping(target = "segmentsResponseDTO", source = "segments")
    BookRecordResponseDTO toResponseDTO (BookRecord source);



    /**
     * Maps a {@link BookRecordDTO} to a {@link BookRecord} entity.
     * The mapping includes the conversion of DTO fields like `itemDTO`, `resourcesDTO`, and `segmentsDTO`
     * into their corresponding entity fields (`item`, `resources`, `segments`).
     *
     * @param source the {@link BookRecordDTO} to be converted
     * @return the mapped {@link BookRecord} entity
     */
    @Mapping(target = "item", source = "itemDTO")
    @Mapping(target = "resources", source = "resourcesDTO")
    @Mapping(target = "segments", source = "segmentsDTO")
    BookRecord toBookRecord (BookRecordDTO source);




}
