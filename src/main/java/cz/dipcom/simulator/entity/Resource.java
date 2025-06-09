package cz.dipcom.simulator.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name ="resources")
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @JsonProperty("djvu_text_file")
    @Column(name = "djvu_text_file")
    private String djvuTextFile;

    @Column(name = "files_count")
    private Integer files;

    @JsonProperty("fulltext_derivative")
    @Column(name = "fulltext_derivative")
    private String fulltextDerivative;

    @JsonProperty("fulltext_file")
    @Column(name = "fulltext_file")
    private String fulltextFile;

    @Column(name = "image")
    private String image;

    @JsonProperty("paprika_resource_path")
    @Column(name = "paprika_resource_path")
    private String paprikaResourcePath;

    @Column(name = "pdf")
    private String pdf;

    @JsonProperty("representative_index")
    @Column(name = "representative_index")
    private Integer representativeIndex;

    @Column(name = "search")
    private String search;

    @Column(name = "segments_count")
    private Integer segments;

    @Column(name = "url")
    private String url;

    @Column(name = "version")
    private Integer version;


    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "book_record_id")
    private BookRecord bookRecord;
}
