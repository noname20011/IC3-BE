package domain.system_study_api.dto.quiz_result;

import java.util.UUID;


public interface TopStudentDTO {
    UUID getId();
    Integer getScore();
    Integer getTime_spent();
    String getFirst_name();
    String getLast_name();
    String getLevel_name();
    String getClass_name();
    String getSchool_name();
    String getPart_name();

    // Default method để gộp tên học sinh trực tiếp
    default String getStudentName() {
        return getLast_name() + " " + getFirst_name();
    }

}
