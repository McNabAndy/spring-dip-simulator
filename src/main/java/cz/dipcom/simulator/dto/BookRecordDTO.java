package cz.dipcom.simulator.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BookRecordDTO {


    @JsonProperty("Object_id")
    private String objectId;

    @JsonProperty("access_restricted")
    private boolean accessRestricted;

    private List<String> aka = new ArrayList<>();

    private List<String> campaigns = new ArrayList<>();


    private List<String> contributor = new ArrayList<>();


    private String date;


    private List<String> dates = new ArrayList<>();


    private List<String> description = new ArrayList<>();

    private boolean digitized;

    @JsonProperty("extract_timestamp")
    private String extractTimestamp ;


    private List<String> group = new ArrayList<>();


    private boolean hassegments;

    @JsonProperty("id")
    private String sourceUrl ;

    @JsonProperty("image_url")
    private List<String> imageUrl = new ArrayList<>();

    private int index;

    private List<String> language = new ArrayList<>();

    @JsonProperty("mime_type")
    private List<String> mimeType = new ArrayList<>();


    private List<String> number = new ArrayList<>();

    @JsonProperty("number_lccn")
    private List<String> numberLccn = new ArrayList<>();

    @JsonProperty("number_oclc")
    private List<String> numberOclc = new ArrayList<>();

    @JsonProperty("number_source_modified")
    private List<String> numberSourceModified = new ArrayList<>();

    @JsonProperty("online_format")
    private List<String> onlineFormat = new ArrayList<>();


    @JsonProperty("original_format")
    private List<String> originalFormat = new ArrayList<>();

    @JsonProperty("other_title")
    private List<String> otherTitle = new ArrayList<>();


    private List<String> partof = new ArrayList<>();

    @JsonProperty("shelf_id")
    private String shelfId;


    private List<String> site = new ArrayList<>();

    private String timestamp;

    private String title;


    private String type;

    private String url;




    @JsonProperty("item")
    private ItemDTO itemDTO;

    @JsonProperty("resources")
    private List<ResourceDTO> resourcesDTO;


    @JsonProperty("segments")
    private List<SegmentDTO> segmentsDTO;


}
