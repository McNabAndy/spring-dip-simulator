package cz.dipcom.simulator.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name ="book_record")
public class BookRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JsonProperty("Object_id")
    @Column(name = "object_id", nullable = false, unique = true)
    private String objectId;

    @Column(name = "access_restricted")
    private boolean accessRestricted;

    @ElementCollection
    @CollectionTable(name = "aka", joinColumns = @JoinColumn(name = "book_record_id"))
    private List<String> aka = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "campaigns", joinColumns = @JoinColumn(name = "book_record_id"))
    private List<String> campaigns = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "contributor", joinColumns = @JoinColumn(name = "book_record_id"))
    private List<String> contributor = new ArrayList<>();

    @Column(name = "date")
    private String date;

    @ElementCollection
    @CollectionTable(name = "dates", joinColumns = @JoinColumn(name = "book_record_id"))
    private List<String> dates = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "description", joinColumns = @JoinColumn(name = "book_record_id"))
    private List<String> description = new ArrayList<>();

    @Column(name = "digitized")
    private boolean digitized;

    @Column(name = "extract_timestamp")
    private String extractTimestamp ;

    @ElementCollection
    @JsonProperty("group")
    @CollectionTable(name = "group_value", joinColumns = @JoinColumn(name = "book_record_id"))
    @Column(name = "group_value")
    private List<String> group = new ArrayList<>();

    @Column(name = "hassegments")
    private boolean hassegments;

    @JsonProperty("id")
    @Column(name = "source_url")
    private String sourceUrl ;

    @ElementCollection
    @CollectionTable(name = "image_url", joinColumns = @JoinColumn(name = "book_record_id"))
    private List<String> imageUrl = new ArrayList<>();

    @JsonProperty("index")
    @Column(name = "index_record")
    private int index;

    @ElementCollection
    @CollectionTable(name = "language", joinColumns = @JoinColumn(name = "book_record_id"))
    private List<String> language = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "mime_type", joinColumns = @JoinColumn(name = "book_record_id"))
    private List<String> mimeType = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "number", joinColumns = @JoinColumn(name = "book_record_id"))
    private List<String> number = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "number_lccn", joinColumns = @JoinColumn(name = "book_record_id"))
    private List<String> numberLccn = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "number_oclc", joinColumns = @JoinColumn(name = "book_record_id"))
    private List<String> numberOclc = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "number_source_modified", joinColumns = @JoinColumn(name = "book_record_id"))
    private List<String> numberSourceModified = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "online_format", joinColumns = @JoinColumn(name = "book_record_id"))
    private List<String> onlineFormat = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "original_format", joinColumns = @JoinColumn(name = "book_record_id"))
    private List<String> originalFormat = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "other_title", joinColumns = @JoinColumn(name = "book_record_id"))
    private List<String> otherTitle = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "partof", joinColumns = @JoinColumn(name = "book_record_id"))
    private List<String> partOf = new ArrayList<>();

    @Column(name = "shelf_id")
    private String shelfId;

    @ElementCollection
    @CollectionTable(name = "site", joinColumns = @JoinColumn(name = "book_record_id"))
    private List<String> site = new ArrayList<>();

    @Column(name = "timestamp")
    private String timestamp;

    @Column(name = "title")
    private String title;

    @Column(name = "type")
    private String type;

    @Column(name = "url")
    private String url;



    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "item_id")
    private Item item;

    @OneToMany(mappedBy = "bookRecord", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Resources> resources;

    @OneToMany(mappedBy = "bookRecord",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Segments> segments;











}
