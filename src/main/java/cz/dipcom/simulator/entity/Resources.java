package cz.dipcom.simulator.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name ="resources")
public class Resources {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "djvu_text_file")
    private String djvuTextFile;

    @Column(name = "files_count")
    private Integer files;

    @Column(name = "fulltext_derivative")
    private String fulltextDerivative;

    @Column(name = "fulltext_file")
    private String fulltextFile;

    @Column(name = "image")
    private String image;

    @Column(name = "paprika_resource_path")
    private String paprikaResourcePath;

    @Column(name = "pdf")
    private String pdf;

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
