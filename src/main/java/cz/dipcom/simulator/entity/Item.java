package cz.dipcom.simulator.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;



/**
 * Item is an entity class representing an item related to a book record in the database.
 * It contains various attributes such as call numbers, contributors, publication dates, formats,
 * languages, and other metadata. This class is mapped to the "item" table in the database and
 * is associated with a specific book record.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name ="item")
public class Item {

    /**
     * The unique identifier for the item in the database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * A list of call numbers associated with the item, stored in the "item_call_number" table.
     * Call numbers are used to classify and locate physical copies in libraries.
     */
    @JsonProperty("call_number")
    @ElementCollection
    @CollectionTable( name = "item_call_number",joinColumns =  @JoinColumn(name = "item_id"))
    @Column(name = "call_number")
    private List<String> call_number;


    /**
     * A list of contributors to the item (e.g., authors, editors), stored in the "item_contributors" table.
     */
    @ElementCollection
    @CollectionTable(name = "item_contributors", joinColumns = @JoinColumn(name = "item_id"))
    @Column(name = "contributor")
    private List<String> contributors = new ArrayList<>();;

    /**
     * A list of dates indicating when the item was created or published, stored in the "item_created_published" table.
     */
    @JsonProperty("created_published")
    @ElementCollection
    @CollectionTable(name = "item_created_published", joinColumns = @JoinColumn(name = "item_id"))
    @Column(name = "created_published")
    private List<String> createdPublished = new ArrayList<>();

    /**
     * The date associated with the item, stored as a string in the "date" column.
     */
    @Column(name = "date", length = 1000)
    private String date;

    /**
     * A list of formats available for the item (e.g., physical, digital), stored in the "item_format" table.
     */
    @ElementCollection
    @CollectionTable(name = "item_format", joinColumns = @JoinColumn(name = "item_id"))
    @Column(name = "format")
    private List<String> format = new ArrayList<>();

    /**
     * A list of languages in which the item is available, stored in the "item_language" table.
     */
    @ElementCollection
    @CollectionTable(name = "item_language", joinColumns = @JoinColumn(name = "item_id"))
    @Column(name = "language")
    private List<String> language = new ArrayList<>();

    /**
     * A list of mediums associated with the item (e.g., print, online), stored in the "item_medium" table.
     */
    @ElementCollection
    @CollectionTable(name = "item_medium", joinColumns = @JoinColumn(name = "item_id"))
    @Column(name = "medium")
    private List<String> medium = new ArrayList<>();

    /**
     * A list of notes providing additional information about the item, stored in the "item_notes" table.
     */
    @ElementCollection
    @CollectionTable(name = "item_notes", joinColumns = @JoinColumn(name = "item_id"))
    @Column(name = "notes", length = 1000)
    private List<String> notes = new ArrayList<>();


    /**
     * The title of the item, stored in the "title" column.
     */
    @Column(name = "title", length = 1000)
    private String title;


    /**
     * The associated book record for this item, mapped to the "bookRecord" field in the BookRecord entity.
     * This is a bidirectional relationship, with the BookRecord entity holding the reference to this item.
     */
    @OneToOne(
            mappedBy = "item",
            cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH},
            fetch = FetchType.EAGER)
    private BookRecord bookRecord;


}
