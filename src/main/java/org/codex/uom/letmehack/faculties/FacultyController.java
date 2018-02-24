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

import java.util.HashMap;
import java.util.Map;

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
    public ResponseEntity<?> createFaculty(@RequestBody Faculty faculty) {
        try {
            faculty = facultyService.createFaculty(faculty);
            return new ResponseEntity<Faculty>(faculty, HttpStatus.CREATED);

        } catch (FacultyExistsException e) {
            ErrorMessage message = new ErrorMessage();
            message.setStatus(409);
            message.setErrorMessage("A faculty with name: " + faculty.getName() + " already exists");
            message.setDeveloperMessage("Faculty creation failed because the faculty name: "+ faculty.getName() +" already exists");
            return new ResponseEntity<ErrorMessage>(message, HttpStatus.valueOf(409));
        }catch (Exception e) {
            ErrorMessage message = new ErrorMessage();
            message.setStatus(400);
            message.setErrorMessage("error creating faculty");
            message.setDeveloperMessage("error occured while creating");
            return new ResponseEntity<ErrorMessage>(message, HttpStatus.valueOf(400));
        }
    }

    @GetMapping()
    public ResponseEntity<?> getAllFaculty() {
        try {
            Iterable<Faculty> resultData = facultyService.getAllFaculty();
            Map<String,Iterable<Faculty>> map = new HashMap<>();
            map.put("faculties", resultData);
            return new ResponseEntity<Map<String,Iterable<Faculty>>>(map, HttpStatus.OK);

        } catch (Exception e) {
            ErrorMessage message = new ErrorMessage();
            message.setDeveloperMessage(e.getMessage());
            message.setErrorMessage("Could not retrieve faculties");
            return new ResponseEntity<ErrorMessage>(message, HttpStatus.valueOf(500));
        }
    }

}
