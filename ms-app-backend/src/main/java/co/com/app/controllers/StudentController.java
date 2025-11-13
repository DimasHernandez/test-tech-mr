package co.com.app.controllers;

import co.com.app.controllers.dto.request.StudentRequest;
import co.com.app.controllers.dto.response.APIResponse;
import co.com.app.controllers.dto.response.StudentResponse;
import co.com.app.dao.projections.StudentProjection;
import co.com.app.mappers.StudentMapper;
import co.com.app.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<APIResponse<List<StudentResponse>>> students() {
        var students = studentService.findAllStudents();
        if (students.isEmpty()) {
            return ResponseEntity.ok(APIResponse.<List<StudentResponse>>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("No students")
                    .data(Collections.emptyList())
                    .build());
        }

        return ResponseEntity.ok(APIResponse.<List<StudentResponse>>builder()
                .message("Students found")
                .status(HttpStatus.OK)
                .data(students)
                .build());
    }

    @GetMapping("/projection/{statusCode}")
    public ResponseEntity<APIResponse<StudentProjection>> studentsProjection(@PathVariable String statusCode) {
        StudentProjection studentProjection = studentService.findByStudentCode(statusCode);
        if (Objects.isNull(studentProjection)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(APIResponse.<StudentProjection>builder()
                .status(HttpStatus.OK)
                .message("Student projection found")
                .data(studentProjection)
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<StudentResponse>> studentById(@PathVariable Long id) {
        try {
            StudentResponse student = studentService.getStudentById(id);
            return ResponseEntity.ok(APIResponse.<StudentResponse>builder()
                    .status(HttpStatus.OK)
                    .message("Student found")
                    .data(student)
                    .build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(APIResponse.<StudentResponse>builder()
                            .status(HttpStatus.NOT_FOUND)
                            .message("Student not found")
                            .data(null)
                            .build());
        }
    }

    @PostMapping
    public ResponseEntity<APIResponse<StudentResponse>> saveStudent(@RequestBody StudentRequest studentRequest) {
        try {
            StudentResponse dto = studentService.createStudent(studentRequest);
            if (Objects.isNull(dto)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                        body(APIResponse.<StudentResponse>builder()
                                .status(HttpStatus.BAD_REQUEST)
                                .message("Student cannot be created")
                                .data(null)
                                .build()
                        );
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    APIResponse.<StudentResponse>builder()
                            .message("Student created")
                            .status(HttpStatus.CREATED)
                            .data(dto)
                            .build()
            );
        } catch (Exception e) {
            return getPleaseTryAgainLater();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<StudentResponse>> updateStudent(@PathVariable Long id, @RequestBody StudentRequest studentRequest) {
        try {
            StudentResponse dto = studentService.updateStudent(id, studentRequest);
            if (Objects.isNull(dto)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                        body(APIResponse.<StudentResponse>builder()
                                .status(HttpStatus.BAD_REQUEST)
                                .message("Student cannot be updated")
                                .data(null)
                                .build()
                        );
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    APIResponse.<StudentResponse>builder()
                            .status(HttpStatus.OK)
                            .message("Student updated")
                            .data(dto)
                            .build()
            );
        } catch (Exception e) {
            return getPleaseTryAgainLater();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Void>> deleteStudent(@PathVariable Long id) {
        try {
            studentService.deleteStudentById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .build();

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    private static ResponseEntity<APIResponse<StudentResponse>> getPleaseTryAgainLater() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(APIResponse.<StudentResponse>builder()
                .message("Please try again later")
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .data(null)
                .build());
    }
}
