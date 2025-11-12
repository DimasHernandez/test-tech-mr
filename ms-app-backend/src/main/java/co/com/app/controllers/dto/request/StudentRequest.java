package co.com.app.controllers.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class StudentRequest {
    private String studentCode;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
}
