package net.demo.myjava.microservices.personservice.exceptions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiError {

    @JsonProperty("method")
    private String method;

    @JsonProperty("requestUri")
    private String requestUri;

    @JsonProperty("status")
    private Integer status;

    @JsonProperty("timestamp")
    private String timestamp;

    @JsonProperty("message")
    private String message;


}
