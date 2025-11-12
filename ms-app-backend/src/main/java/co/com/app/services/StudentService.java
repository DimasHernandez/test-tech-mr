package co.com.app.services;

import co.com.app.controllers.dto.request.StudentDTO;
import co.com.app.dao.projections.StudentProjection;

import java.util.List;

public interface StudentService {
    StudentProjection findByStudentCode(String studentCode);

    StudentDTO createStudent(StudentDTO dto);

    List<StudentDTO> findAllStudents();

    void deleteStudentById(Long id);

}
