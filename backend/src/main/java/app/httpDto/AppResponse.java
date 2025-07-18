package app.httpDto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class AppResponse {
    protected String timeStamp;
    protected HttpStatus status;
    protected Integer statusCode;
    protected String message;
    protected String path;
    protected String method;
    protected Map<?, ?> data;
}
