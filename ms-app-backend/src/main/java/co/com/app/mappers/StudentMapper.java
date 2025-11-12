package co.com.app.mappers;

import co.com.app.controllers.dto.request.StudentRequest;
import co.com.app.controllers.dto.response.StudentResponse;
import co.com.app.models.Student;
import org.mapstruct.Mapper;

@Mapper
public interface StudentMapper {

    StudentResponse toResponse(StudentRequest studentRequest);

    StudentRequest toRequest(StudentResponse student);

    Student toEntity(StudentRequest studentRequest);

    StudentResponse toDTOResponse(Student student);
}
