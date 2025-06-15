package cz.dipcom.simulator.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


/**
 * BookRecord is an entity class representing a book record stored in the database.
 * It contains various attributes that describe the properties of a book, including metadata,
 * contributors, formats, identification numbers, and associated resources and segments.
 * This entity is mapped to the "book_record" table in the database.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name ="book_record")
public class BookRecord {

    /**
     * The unique identifier of the book record in the database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long bookId;


    /**
     * The unique object ID of the book record.
     */
    @JsonProperty("Object_id")
    @Column(name = "object_id", nullable = false, unique = true)
    private String objectId;


    /**
     * A flag indicating whether access to the book is restricted.
     */
    @JsonProperty("access_restricted")
    @Column(name = "access_restricted")
    private boolean accessRestricted;


    /**
     * A list of alternate names or acronyms for the book.
     */
    @ElementCollection
    @CollectionTable(name = "aka", joinColumns = @JoinColumn(name = "book_record_id"))
    private List<String> aka = new ArrayList<>();


    /**
     * A list of campaigns associated with the book.
     */
    @ElementCollection
    @CollectionTable(name = "campaigns", joinColumns = @JoinColumn(name = "book_record_id"))
    private List<String> campaigns = new ArrayList<>();


    /**
     * A list of contributors to the book.
     */
    @ElementCollection
    @CollectionTable(name = "contributor", joinColumns = @JoinColumn(name = "book_record_id"))
    private List<String> contributor = new ArrayList<>();


    /**
     * The date associated with the book record.
     */
    @Column(name = "date")
    private String date;


    /**
     * A list of dates associated with the book record.
     */
    @ElementCollection
    @CollectionTable(name = "dates", joinColumns = @JoinColumn(name = "book_record_id"))
    private List<String> dates = new ArrayList<>();


    /**
     * A list of descriptions for the book.
     */
    @ElementCollection
    @CollectionTable(name = "description", joinColumns = @JoinColumn(name = "book_record_id"))
    @Column(name = "description", length = 1000)
    private List<String> description = new ArrayList<>();


    /**
     * A flag indicating whether the book is digitized.
     */
    @Column(name = "digitized")
    private boolean digitized;


    /**
     * The timestamp when the book record was extracted.
     */
    @JsonProperty("extract_timestamp")
    @Column(name = "extract_timestamp")
    private String extractTimestamp ;


    /**
     * A list of groups associated with the book.
     */
    @ElementCollection
    @JsonProperty("group")
    @CollectionTable(name = "group_value", joinColumns = @JoinColumn(name = "book_record_id"))
    @Column(name = "group_value")
    private List<String> group = new ArrayList<>();


    /**
     * A flag indicating whether the book has segments.
     */
    @Column(name = "hassegments")
    private boolean hassegments;


    /**
     * The source URL of the book record.
     */
    @JsonProperty("id")
    @Column(name = "source_url")
    private String sourceUrl ;


    /**
     * A list of image URLs related to the book.
     */
    @JsonProperty("image_url")
    @ElementCollection
    @CollectionTable(name = "image_url", joinColumns = @JoinColumn(name = "book_record_id"))
    private List<String> imageUrl = new ArrayList<>();


    /**
     * The index of the book record.
     */
    @JsonProperty("index")
    @Column(name = "index_record")
    private int index;


    /**
     * A list of languages the book is available in.
     */
    @ElementCollection
    @CollectionTable(name = "language", joinColumns = @JoinColumn(name = "book_record_id"))
    private List<String> language = new ArrayList<>();

    /**
     * A list of MIME types associated with the book.
     */
    @JsonProperty("mime_type")
    @ElementCollection
    @CollectionTable(name = "mime_type", joinColumns = @JoinColumn(name = "book_record_id"))
    private List<String> mimeType = new ArrayList<>();


    /**
     * A list of numbers associated with the book (e.g., ISBN, LCCN).
     */
    @ElementCollection
    @CollectionTable(name = "number", joinColumns = @JoinColumn(name = "book_record_id"))
    private List<String> number = new ArrayList<>();


    /**
     * A list of LCCN (Library of Congress Control Number) entries for the book.
     */
    @JsonProperty("number_lccn")
    @ElementCollection
    @CollectionTable(name = "number_lccn", joinColumns = @JoinColumn(name = "book_record_id"))
    private List<String> numberLccn = new ArrayList<>();


    /**
     * A list of OCLC (Online Computer Library Center) numbers for the book.
     */
    @JsonProperty("number_oclc")
    @ElementCollection
    @CollectionTable(name = "number_oclc", joinColumns = @JoinColumn(name = "book_record_id"))
    private List<String> numberOclc = new ArrayList<>();


    /**
     * A list of modified source numbers for the book.
     */
    @JsonProperty("number_source_modified")
    @ElementCollection
    @CollectionTable(name = "number_source_modified", joinColumns = @JoinColumn(name = "book_record_id"))
    private List<String> numberSourceModified = new ArrayList<>();


    /**
     * A list of online formats available for the book.
     */
    @JsonProperty("online_format")
    @ElementCollection
    @CollectionTable(name = "online_format", joinColumns = @JoinColumn(name = "book_record_id"))
    private List<String> onlineFormat = new ArrayList<>();


    /**
     * A list of original formats of the book.
     */
    @JsonProperty("original_format")
    @ElementCollection
    @CollectionTable(name = "original_format", joinColumns = @JoinColumn(name = "book_record_id"))
    private List<String> originalFormat = new ArrayList<>();


    /**
     * A list of other titles associated with the book.
     */
    @JsonProperty("other_title")
    @ElementCollection
    @CollectionTable(name = "other_title", joinColumns = @JoinColumn(name = "book_record_id"))
    private List<String> otherTitle = new ArrayList<>();


    /**
     * A list of related parts or editions of the book.
     */
    @ElementCollection
    @CollectionTable(name = "partof", joinColumns = @JoinColumn(name = "book_record_id"))
    private List<String> partof = new ArrayList<>();


    /**
     * The shelf ID associated with the book.
     */
    @JsonProperty("shelf_id")
    @Column(name = "shelf_id")
    private String shelfId;


    /**
     * A list of sites associated with the book.
     */
    @ElementCollection
    @CollectionTable(name = "site", joinColumns = @JoinColumn(name = "book_record_id"))
    private List<String> site = new ArrayList<>();


    /**
     * The timestamp of the book record.
     */
    @Column(name = "timestamp")
    private String timestamp;


    /**
     * The title of the book.
     */
    @Column(name = "title", length = 1000)
    private String title;


    /**
     * The type of the book record (e.g., book, article).
     */
    @Column(name = "type")
    private String type;


    /**
     * The URL of the book record.
     */
    @Column(name = "url")
    private String url;





    /**
     * The item associated with the book record.
     */
    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "item_id")
    private Item item;


    /**
     * The resources related to the book record.
     */
    @OneToMany(
            mappedBy = "bookRecord",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private List<Resource> resources = new ArrayList<>();


    /**
     * The segments associated with the book record.
     */
    @OneToMany(
            mappedBy = "bookRecord",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private List<Segment> segments = new ArrayList<>();




}
