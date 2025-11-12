package co.com.app.services.impl;

import co.com.app.controllers.dto.request.StudentDTO;
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
    public StudentDTO createStudent(StudentDTO dto) {
        try {
            Student student = mapper.toEntity(dto);
            return mapper.toDto(repository.save(student));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<StudentDTO> findAllStudents() {
        List<Student> students = (List<Student>) repository.findAll();
        return students.stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public void deleteStudentById(Long id) {
        if (Objects.nonNull(id) && repository.existsById(id)) {
            repository.deleteById(id);
        }
    }
}
