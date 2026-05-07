package domain.system_study_api.entity.model;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

// Dùng để chứa các dữ liệu biến đổi như Options, Hotspots, Pairs
@Data
public class QuestionMetadata implements Serializable {
    private List<Option> options;      // Cho Single/Multiple/Reorder/TrueFalse
    private List<Pair> pairs;          // Cho Matching
    private List<Hotspot> hotSpots;    // Cho Hotspot
    private List<String> labels;       // Ví dụ: ["Có", "Không"]
}

@Data
class Option {
    private Long id;
    private String value;
    private Boolean isCorrect;
    private Integer orderIndex;
}

@Data
class Pair {
    private String left;
    private String right;
}

@Data
class Hotspot {
    private String label;
    private Double x, y, w, h;
    private Boolean isCorrect;
}
