package cz.dipcom.simulator.dto.response;


import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * ItemResponseDTO is a Data Transfer Object (DTO) that represents the response data for an item
 * associated with a book record. It contains attributes such as call numbers, contributors,
 * creation or publication dates, formats, languages, and more. This DTO is typically used
 * to transfer item-related information in API responses.
 */
@Getter
@Setter
@NoArgsConstructor
public class ItemResponseDTO {


    /**
     * A list of call numbers associated with the item.
     * Call numbers are used to classify and locate physical copies in libraries.
     */
    @JsonProperty("call_number")
    private List<String> call_number;


    /**
     * A list of contributors to the item (e.g., authors, editors, etc.).
     */
    private List<String> contributors = new ArrayList<>();;


    /**
     * A list of dates indicating when the item was created or published.
     */
    @JsonProperty("created_published")
    private List<String> createdPublished = new ArrayList<>();


    /**
     * A specific date associated with the item (e.g., publication date).
     */
    private String date;


    /**
     * A list of formats available for the item (e.g., physical, digital, eBook).
     */
    private List<String> format = new ArrayList<>();



    /**
     * A list of languages in which the item is available.
     */
    private List<String> language = new ArrayList<>();


    /**
     * A list of mediums associated with the item (e.g., print, online).
     */
    private List<String> medium = new ArrayList<>();



    /**
     * A list of notes providing additional information or comments about the item.
     */
    private List<String> notes = new ArrayList<>();


    /**
     * The title of the item.
     */
    private String title;

}
