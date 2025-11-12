package co.com.app.mappers;

import co.com.app.controllers.dto.request.StudentRequest;
import co.com.app.controllers.dto.response.StudentResponse;
import co.com.app.models.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface StudentMapper {

    StudentResponse toResponse(StudentRequest studentRequest);

    StudentRequest toRequest(StudentResponse student);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "studentCode", target = "studentCode")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "dateOfBirth", target = "dateOfBirth")
    Student toEntity(StudentRequest studentRequest);

    StudentResponse toDTOResponse(Student student);
}
