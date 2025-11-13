package co.com.app.services.impl;

import co.com.app.controllers.dto.request.StudentRequest;
import co.com.app.controllers.dto.response.StudentResponse;
import co.com.app.dao.StudentDAORepository;
import co.com.app.dao.projections.StudentProjection;
import co.com.app.mappers.StudentMapper;
import co.com.app.models.Student;
import co.com.app.services.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentDAORepository repository;
    private final StudentMapper mapper;

    @Override
    public StudentProjection findByStudentCode(String studentCode) {
        return repository.findByStudentCode(studentCode);
    }

    @Override
    public StudentResponse createStudent(StudentRequest dto) {
        try {
            Student student = mapper.toEntity(dto);
            return mapper.toDTOResponse(repository.save(student));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<StudentResponse> findAllStudents() {
        List<Student> students = repository.findAll();
        return students.stream()
                .map(mapper::toDTOResponse)
                .toList();
    }

    @Override
    public StudentResponse updateStudent(Long id, StudentRequest dto) {
        try {
            Student studentToUpdate = findStudentById(id);

            studentToUpdate.setFirstName(dto.getFirstName());
            studentToUpdate.setLastName(dto.getLastName());
            studentToUpdate.setDateOfBirth(dto.getDateOfBirth());
            studentToUpdate.setStudentCode(dto.getStudentCode());

            Student studentSaved = repository.save(studentToUpdate);
            return mapper.toDTOResponse(studentSaved);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void deleteStudentById(Long id) {
        if (Objects.nonNull(id) && repository.existsById(id)) {
            repository.deleteById(id);
        }
    }

    private Student findStudentById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }
}
