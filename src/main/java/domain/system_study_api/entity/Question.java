package domain.system_study_api.entity;

import domain.system_study_api.constants.QuestionTypeEnum;
import domain.system_study_api.entity.base.BaseEntityHasId;
import domain.system_study_api.helper.utils.JsonToMapConverter;
import jakarta.persistence.*;
import lombok.*;

import java.util.Map;

@Entity
@Table(name = "tbl_question", indexes = {
        @Index(name = "idx_question_part", columnList = "part_id")
})

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Question extends BaseEntityHasId {

    @Enumerated(EnumType.STRING)
    @Column(name = "question_type")
    private QuestionTypeEnum type;

    @Column(columnDefinition = "TEXT")
    private String text;

    private String imageUrl;

    // Sử dụng Hibernate @JdbcTypeCode(SqlTypes.JSON) cho MySQL 8+
    // Hoặc lưu dạng String và convert bằng Jackson Converter
//    @Type(JsonType.class)
    @Column(columnDefinition = "LONGTEXT")
    @Convert(converter = JsonToMapConverter.class)
    private Map<String, Object> metadata;

    @Column(columnDefinition = "TEXT")
    private String explanation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "part_id", nullable = false)
    private Part part;  // OT1, OT2, OT3
}

