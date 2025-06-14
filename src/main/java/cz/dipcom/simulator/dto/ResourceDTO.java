package cz.dipcom.simulator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResourceDTO {


    @JsonProperty("djvu_text_file")
    private String djvuTextFile;


    private Integer files;

    @JsonProperty("fulltext_derivative")
    private String fulltextDerivative;

    @JsonProperty("fulltext_file")

    private String fulltextFile;


    private String image;


    @JsonProperty("paprika_resource_path")
    private String paprikaResourcePath;


    private String pdf;


    @JsonProperty("representative_index")
    private Integer representativeIndex;


    private String search;


    private Integer segments;


    private String url;


    private Integer version;



}
