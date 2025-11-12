package co.com.app.dao;

import co.com.app.dao.projections.StudentProjection;
import co.com.app.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentDAORepository extends JpaRepository<Student, Long> {
    StudentProjection findByStudentCode(String studentCode);
}
