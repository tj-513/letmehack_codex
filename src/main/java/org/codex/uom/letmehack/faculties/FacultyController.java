package org.codex.uom.letmehack.faculties;

import org.codex.uom.letmehack.common.ErrorMessage;
import org.codex.uom.letmehack.dto.FacultyDto;
import org.codex.uom.letmehack.user.User;
import org.codex.uom.letmehack.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * Created by TJR on 2/24/2018.
 */
@RestController
@CrossOrigin(origins = {"http://localhost:8090"}, allowCredentials = "false")
@RequestMapping("api/faculties")
public class FacultyController {
    @Autowired
    private FacultyService facultyService;

    @PostMapping()
    public ResponseEntity<?> createFaculty(Faculty faculty) {
        try {
            faculty = facultyService.createFaculty(faculty);
            FacultyDto facultyDto = new FacultyDto(faculty);
            return new ResponseEntity<FacultyDto>(facultyDto, HttpStatus.OK);

        } catch (Exception e) {
            ErrorMessage message = new ErrorMessage();
            message.setDeveloperMessage("set appropriate messsage here");
            message.setErrorMessage("set appropriate messsage here");
            return new ResponseEntity<ErrorMessage>(message, HttpStatus.valueOf(400));
        }
    }

    @GetMapping()
    public ResponseEntity<?> getAllFaculty() {
        try {
            Iterable<Faculty> resultData = facultyService.getAllFaculty();
            return new ResponseEntity<Iterable<Faculty>>(resultData, HttpStatus.OK);

        } catch (Exception e) {
            ErrorMessage message = new ErrorMessage();
            message.setDeveloperMessage("set appropriate messsage here");
            message.setErrorMessage("set appropriate messsage here");
            return new ResponseEntity<ErrorMessage>(message, HttpStatus.valueOf(400));
        }
    }

}
