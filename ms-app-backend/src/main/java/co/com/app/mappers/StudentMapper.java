package co.com.app.mappers;

import co.com.app.controllers.dto.request.StudentDTO;
import co.com.app.models.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface StudentMapper {

    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "studentCode", source = "studentCode")
    @Mapping(target = "dateOfBirth", source = "dateOfBirth")
    Student toEntity(StudentDTO studentDTO);

    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "studentCode", source = "studentCode")
    @Mapping(target = "dateOfBirth", source = "dateOfBirth")
    StudentDTO toDto(Student student);
}
