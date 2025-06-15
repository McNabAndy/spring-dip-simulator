package cz.dipcom.simulator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


/**
 * BookRecordResponseDTO is a Data Transfer Object (DTO) that represents the response data
 * for a book record. It includes various attributes that describe the properties of a book,
 * such as metadata, contributors, formats, identification numbers, and related resources.
 * This DTO is typically used for returning book record data in API responses.
 */
@Getter
@Setter
@NoArgsConstructor
public class BookRecordResponseDTO {


    /**
     * The unique identifier for the book record in the database.
     */
    private Long bookId;

    /**
     * The unique object ID of the book record.
     */
    @JsonProperty("Object_id")
    private String objectId;

    /**
     * A flag indicating whether access to the book is restricted.
     */
    @JsonProperty("access_restricted")
    private boolean accessRestricted;

    /**
     * A list of alternate names or acronyms for the book.
     */
    private List<String> aka = new ArrayList<>();

    /**
     * A list of campaigns associated with the book.
     */
    private List<String> campaigns = new ArrayList<>();

    /**
     * A list of contributors to the book.
     */
    private List<String> contributor = new ArrayList<>();

    /**
     * The date of the book record.
     */
    private String date;

    /**
     * A list of date entries related to the book.
     */
    private List<String> dates = new ArrayList<>();

    /**
     * A list of descriptions of the book.
     */
    private List<String> description = new ArrayList<>();


    /**
     * A flag indicating whether the book is digitized.
     */
    private boolean digitized;


    /**
     * The timestamp of when the book record was extracted.
     */
    @JsonProperty("extract_timestamp")
    private String extractTimestamp ;


    /**
     * A list of groups associated with the book.
     */
    private List<String> group = new ArrayList<>();


    /**
     * A flag indicating whether the book has segments.
     */
    private boolean hassegments;


    /**
     * The source URL of the book record.
     */
    @JsonProperty("id")
    private String sourceUrl ;


    /**
     * A list of image URLs related to the book.
     */
    @JsonProperty("image_url")
    private List<String> imageUrl = new ArrayList<>();

    /**
     * The index of the book record.
     */
    private int index;


    /**
     * A list of languages the book is available in.
     */
    private List<String> language = new ArrayList<>();


    /**
     * A list of MIME types associated with the book.
     */
    @JsonProperty("mime_type")
    private List<String> mimeType = new ArrayList<>();

    /**
     * A list of numbers associated with the book (e.g., ISBN, LCCN).
     */
    private List<String> number = new ArrayList<>();


    /**
     * A list of LCCN (Library of Congress Control Number) entries for the book.
     */
    @JsonProperty("number_lccn")
    private List<String> numberLccn = new ArrayList<>();


    /**
     * A list of OCLC (Online Computer Library Center) numbers for the book.
     */
    @JsonProperty("number_oclc")
    private List<String> numberOclc = new ArrayList<>();


    /**
     * A list of modified source numbers for the book.
     */
    @JsonProperty("number_source_modified")
    private List<String> numberSourceModified = new ArrayList<>();


    /**
     * A list of online formats available for the book.
     */
    @JsonProperty("online_format")
    private List<String> onlineFormat = new ArrayList<>();


    /**
     * A list of original formats of the book.
     */
    @JsonProperty("original_format")
    private List<String> originalFormat = new ArrayList<>();


    /**
     * A list of other titles associated with the book.
     */
    @JsonProperty("other_title")
    private List<String> otherTitle = new ArrayList<>();


    /**
     * A list of related parts or editions of the book.
     */
    private List<String> partof = new ArrayList<>();


    /**
     * The shelf ID associated with the book.
     */
    @JsonProperty("shelf_id")
    private String shelfId;


    /**
     * A list of sites associated with the book.
     */
    private List<String> site = new ArrayList<>();


    /**
     * The timestamp associated with the book record.
     */
    private String timestamp;


    /**
     * The title of the book.
     */
    private String title;


    /**
     * The type of the book record (e.g., book, article).
     */
    private String type;

    /**
     * The URL of the book record.
     */
    private String url;



    /**
     * The item associated with the book record.
     */
    @JsonProperty("item")
    private ItemResponseDTO itemResponseDTO;

    /**
     * The resources related to the book record.
     */
    @JsonProperty("resources")
    private List<ResourceResponseDTO> resourcesResponseDTO;

    /**
     * The segments associated with the book record.
     */
    @JsonProperty("segments")
    private List<SegmentResponseDTO> segmentsResponseDTO;




}
