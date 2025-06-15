package cz.dipcom.simulator.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * SegmentResponseDTO is a Data Transfer Object (DTO) that represents the response data
 * for a segment associated with a resource. It contains details such as the segment's count, link,
 * and URL. This DTO is used to transfer segment-related information in API responses.
 */
@Getter
@Setter
@NoArgsConstructor
public class SegmentResponseDTO {

    /**
     * The count of segments related to the resource or item.
     */
    private Integer count;


    /**
     * The link associated with the segment, which could be a reference or related resource.
     */
    private String link;


    /**
     * The URL of the segment, which may point to an online resource or file.
     */
    private String url;
}
