package net.demo.myjava.microservices.personservice.exceptions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiValidationError {

    public ApiValidationError field(String field) {
        this.field = field;
        return this;
    }

    @JsonProperty("field")
    private String field = null;

    @JsonProperty("rejectedValue")
    private String rejectedValue = null;

    @JsonProperty("message")
    private String message = null;

}
