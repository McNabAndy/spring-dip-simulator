package cz.dipcom.simulator.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Resource is an entity class representing a resource associated with a book record.
 * It contains various attributes such as file paths for DjVu, full-text files, images, PDFs, and other metadata.
 * The class is mapped to the "resources" table in the database and is linked to the corresponding book record.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name ="resources")
public class Resource {

    /**
     * The unique identifier for the resource in the database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;


    /**
     * The path to the DjVu text file associated with the resource.
     */
    @JsonProperty("djvu_text_file")
    @Column(name = "djvu_text_file")
    private String djvuTextFile;


    /**
     * The number of files associated with the resource.
     */
    @Column(name = "files_count")
    private Integer files;


    /**
     * The path to the full-text derivative of the resource.
     */
    @JsonProperty("fulltext_derivative")
    @Column(name = "fulltext_derivative")
    private String fulltextDerivative;

    /**
     * The path to the full-text file of the resource.
     */
    @JsonProperty("fulltext_file")
    @Column(name = "fulltext_file")
    private String fulltextFile;


    /**
     * The image associated with the resource (e.g., a cover image).
     */
    @Column(name = "image")
    private String image;


    /**
     * The path to the paprika resource associated with the resource.
     */
    @JsonProperty("paprika_resource_path")
    @Column(name = "paprika_resource_path")
    private String paprikaResourcePath;


    /**
     * The path to the PDF file associated with the resource.
     */
    @Column(name = "pdf")
    private String pdf;


    /**
     * The representative index for the resource.
     */
    @JsonProperty("representative_index")
    @Column(name = "representative_index")
    private Integer representativeIndex;

    /**
     * The search string or query related to the resource.
     */
    @Column(name = "search")
    private String search;


    /**
     * The number of segments in the resource.
     */
    @Column(name = "segments_count")
    private Integer segments;


    /**
     * The URL for accessing the resource.
     */
    @Column(name = "url")
    private String url;


    /**
     * The version of the resource.
     */
    @Column(name = "version")
    private Integer version;


    /**
     * The book record associated with the resource.
     * This is a many-to-one relationship, where a resource is linked to one book record.
     */
    @ManyToOne(
            cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH},
            fetch = FetchType.EAGER )
    @JoinColumn(name = "book_record_id")
    private BookRecord bookRecord;
}
