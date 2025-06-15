package cz.dipcom.simulator.dto.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ApiErrorDTO is a Data Transfer Object (DTO) used to represent the error details in API responses.
 * It contains information about the HTTP status code, a descriptive error message, and a timestamp
 * of when the error occurred.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiErrorDTO {

    /**
     * The HTTP status code associated with the error (e.g., 404 for Not Found, 500 for Internal Server Error).
     */
    private int statusCode;

    /**
     * A message providing details about the error. This can be an explanation or a description of the issue.
     */
    private String message;

    /**
     * The timestamp of when the error occurred, in milliseconds since the epoch.
     */
    private long timestamp;
}
