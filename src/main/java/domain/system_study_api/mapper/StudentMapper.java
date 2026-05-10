package domain.system_study_api.mapper;

import domain.system_study_api.dto.classroom.ClassroomRequestDTO;
import domain.system_study_api.dto.classroom.ClassroomResponseDTO;
import domain.system_study_api.dto.student.StudentRequestDTO;
import domain.system_study_api.dto.student.StudentResponseDTO;
import domain.system_study_api.entity.Classroom;
import domain.system_study_api.entity.Student;
import domain.system_study_api.helper.base.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface StudentMapper extends BaseMapper<Student, StudentRequestDTO, StudentResponseDTO> {


    @Override
    @Mapping(target = "name", expression = "java(student.getLastName() + \" \" + student.getFirstName())")
    @Mapping(target = "className", source = "student.classroom.name")
    @Mapping(target = "schoolName", source = "student.classroom.school.name")
    StudentResponseDTO mapToResponseDto(Student student);
}
