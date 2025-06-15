package cz.dipcom.simulator.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * SegmentDTO is a Data Transfer Object (DTO) that represents a segment associated with a resource.
 * It contains information about the segment's count, link, and URL. This DTO is typically used
 * for transferring segment-related data in API responses or requests.
 */
@Getter
@Setter
@NoArgsConstructor
public class SegmentDTO {

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
