package cz.dipcom.simulator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ResourceResponseDTO is a Data Transfer Object (DTO) that represents the response data
 * for a resource associated with a book record. It includes details such as DjVu text files,
 * full-text derivatives, images, PDFs, and other relevant resource paths or links.
 * This DTO is typically used to return resource-related information in API responses.
 */
@Getter
@Setter
@NoArgsConstructor
public class ResourceResponseDTO {


    /**
     * The path to the DjVu text file associated with the resource.
     */
    @JsonProperty("djvu_text_file")
    private String djvuTextFile;


    /**
     * The number of files associated with the resource.
     */
    private Integer files;


    /**
     * The path to the full-text derivative of the resource.
     */
    @JsonProperty("fulltext_derivative")
    private String fulltextDerivative;


    /**
     * The path to the full-text file of the resource.
     */
    @JsonProperty("fulltext_file")
    private String fulltextFile;


    /**
     * The image associated with the resource (e.g., a cover image).
     */
    private String image;

    /**
     * The path to the paprika resource associated with the resource.
     */
    @JsonProperty("paprika_resource_path")
    private String paprikaResourcePath;

    /**
     * The path to the PDF file associated with the resource.
     */
    private String pdf;


    /**
     * The representative index for the resource.
     */
    @JsonProperty("representative_index")
    private Integer representativeIndex;


    /**
     * The search string or query related to the resource.
     */
    private String search;


    /**
     * The number of segments in the resource.
     */
    private Integer segments;

    /**
     * The URL of the resource.
     */
    private String url;


    /**
     * The version of the resource.
     */
    private Integer version;


}
