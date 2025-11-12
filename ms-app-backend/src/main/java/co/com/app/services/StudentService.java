package co.com.app.services;

import co.com.app.controllers.dto.request.StudentRequest;
import co.com.app.controllers.dto.response.StudentResponse;
import co.com.app.dao.projections.StudentProjection;

import java.util.List;

public interface StudentService {
    StudentProjection findByStudentCode(String studentCode);

    StudentResponse createStudent(StudentRequest dto);

    List<StudentResponse> findAllStudents();

    void deleteStudentById(Long id);

}
