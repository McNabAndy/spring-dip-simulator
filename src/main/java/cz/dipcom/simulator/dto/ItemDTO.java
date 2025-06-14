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
public class ItemDTO {

    @JsonProperty("call_number")
    private List<String> call_number;


    private List<String> contributors = new ArrayList<>();;


    @JsonProperty("created_published")
    private List<String> createdPublished = new ArrayList<>();


    private String date;


    private List<String> format = new ArrayList<>();



    private List<String> language = new ArrayList<>();


    private List<String> medium = new ArrayList<>();



    private List<String> notes = new ArrayList<>();


    private String title;


}
