package co.com.app.controllers.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentResponse {
    private Long id;
    private String studentCode;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
}
