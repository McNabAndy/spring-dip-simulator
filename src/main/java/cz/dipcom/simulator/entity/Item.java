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
@Table(name ="item")
public class Item {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @JsonProperty("call_number")
    @ElementCollection
    @CollectionTable( name = "item_call_number",joinColumns =  @JoinColumn(name = "item_id"))
    @Column(name = "call_number")
    private List<String> call_number;


    @ElementCollection
    @CollectionTable(name = "item_contributors", joinColumns = @JoinColumn(name = "item_id"))
    @Column(name = "contributor")
    private List<String> contributors = new ArrayList<>();;


    @JsonProperty("created_published")
    @ElementCollection
    @CollectionTable(name = "item_created_published", joinColumns = @JoinColumn(name = "item_id"))
    @Column(name = "created_published")
    private List<String> createdPublished = new ArrayList<>();

    @Column(name = "date", length = 1000)
    private String date;


    @ElementCollection
    @CollectionTable(name = "item_format", joinColumns = @JoinColumn(name = "item_id"))
    @Column(name = "format")
    private List<String> format = new ArrayList<>();


    @ElementCollection
    @CollectionTable(name = "item_language", joinColumns = @JoinColumn(name = "item_id"))
    @Column(name = "language")
    private List<String> language = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "item_medium", joinColumns = @JoinColumn(name = "item_id"))
    @Column(name = "medium")
    private List<String> medium = new ArrayList<>();


    @ElementCollection
    @CollectionTable(name = "item_notes", joinColumns = @JoinColumn(name = "item_id"))
    @Column(name = "notes", length = 1000)
    private List<String> notes = new ArrayList<>();

    @Column(name = "title", length = 1000)
    private String title;



    @OneToOne(mappedBy = "item", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    BookRecord bookRecord;


}
