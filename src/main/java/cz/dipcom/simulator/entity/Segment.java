package cz.dipcom.simulator.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Segment is an entity class representing a segment of a book or resource.
 * It contains details about the segment such as its count, link, and URL.
 * The class is mapped to the "segments" table in the database and is linked to a specific book record.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name ="segments")
public class Segment {

    /**
     * The unique identifier for the segment in the database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    /**
     * The count of segments related to the resource.
     */
    @Column(name = "count")
    private Integer count;


    /**
     * The link associated with the segment, which could be a reference or related resource.
     */
    @Column(name = "link")
    private String link;


    /**
     * The URL of the segment, which may point to an online resource or file.
     */
    @Column(name = "url")
    private String url;


    /**
     * The book record associated with this segment.
     * This is a many-to-one relationship, where a segment is linked to one book record.
     */
    @ManyToOne(
            cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH},
            fetch = FetchType.EAGER)
    @JoinColumn(name = "book_record_id")
    private BookRecord bookRecord;
}
