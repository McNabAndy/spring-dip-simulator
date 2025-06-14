package cz.dipcom.simulator.dto.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiErrorDTO {

    private int statusCode;

    private String message;

    private long timestamp;
}
