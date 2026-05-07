package domain.system_study_api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseData<T> {
    private int status;
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public ResponseData(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
