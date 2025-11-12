package co.com.app.controllers;

import co.com.app.controllers.dto.request.StudentDTO;
import co.com.app.controllers.dto.response.APIResponse;
import co.com.app.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<APIResponse<List<StudentDTO>>> students() {
        var students = studentService.findAllStudents();
        if (students.isEmpty()) {
            return ResponseEntity.ok(APIResponse.<List<StudentDTO>>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("No students")
                    .data(Collections.emptyList())
                    .build());
        }

        return ResponseEntity.ok(APIResponse.<List<StudentDTO>>builder()
                .message("Students found")
                .status(HttpStatus.OK)
                .data(students)
                .build());
    }

    @PostMapping
    public ResponseEntity<APIResponse<StudentDTO>> saveStudent(@RequestBody StudentDTO studentDTO) {
        try {
            StudentDTO dto = studentService.createStudent(studentDTO);
            if (Objects.isNull(dto)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                        body(APIResponse.<StudentDTO>builder()
                                .status(HttpStatus.BAD_REQUEST)
                                .message("Student cannot be created")
                                .data(null)
                                .build()
                        );
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    APIResponse.<StudentDTO>builder()
                            .message("Student created")
                            .status(HttpStatus.CREATED)
                            .data(dto)
                            .build()
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(APIResponse.<StudentDTO>builder()
                    .message("Please try again later")
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .data(null)
                    .build());
        }
    }
}
