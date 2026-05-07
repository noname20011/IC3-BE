package domain.system_study_api.mapper.convert_helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class jsonToMapHelper {

    private final ObjectMapper objectMapper;

    @Named("jsonToMap")
    public Map<String, Object> stringToMap(String source) {
        if (source == null || source.isEmpty()) {
            return null;
        }
        try {
            return objectMapper.readValue(source, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Định dạng JSON không hợp lệ: " + e.getMessage());
        }
    }


    @Named("mapToJson")
    public String mapToString(Map<String, Object> source) {
        if (source == null) {
            return null;
        }
        try {
            return objectMapper.writeValueAsString(source);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}