package domain.system_study_api.mapper;

import domain.system_study_api.dto.quiz_result.QuizResultRequestDTO;
import domain.system_study_api.dto.quiz_result.QuizResultResponseDTO;
import domain.system_study_api.entity.QuizResult;
import domain.system_study_api.helper.base.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface QuizResultMapper extends BaseMapper<QuizResult, QuizResultRequestDTO, QuizResultResponseDTO> {


    @Override
    @Mapping(target = "studentName", expression = "java(quizResult.getStudent().getLastName() + \" \" + quizResult.getStudent().getFirstName())")
    @Mapping(target = "className", source = "quizResult.student.classroom.className")
    @Mapping(target = "schoolName", source = "quizResult.student.classroom.school.schoolName")
    @Mapping(target = "partName", source = "quizResult.part.name")
    @Mapping(target = "levelName", source = "quizResult.part.level.name")
    QuizResultResponseDTO mapToResponseDto(QuizResult quizResult);
}
