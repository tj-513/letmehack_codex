package org.codex.uom.letmehack.departments;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.codex.uom.letmehack.common.ErrorMessage;
import org.codex.uom.letmehack.faculties.FacultyRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by TJR on 2/24/2018.
 */
@RestController
@CrossOrigin(origins = {"http://localhost:8090"}, allowCredentials = "false")
@RequestMapping("api/departments")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private FacultyRespository facultyRespository;
    @PostMapping()
    public ResponseEntity<?> createDepartment(@RequestBody Department department) {
        try {
            department = departmentService.createDepartment(department);
            DepartmentDTO dto = new DepartmentDTO(department);
            dto.setFaculty(facultyRespository.findOne(department.getFacultyId()));

            return new ResponseEntity<DepartmentDTO>(dto, HttpStatus.CREATED);

        } catch (DepartmentExistsException e) {
            ErrorMessage message = new ErrorMessage();
            message.setStatus(409);
            message.setErrorMessage("A department with name: " + department.getName() + " already exists");
            message.setDeveloperMessage("Department creation failed because the department name: "+ department.getName() +" already exists");
            return new ResponseEntity<ErrorMessage>(message, HttpStatus.valueOf(409));
        } catch (InvalidFacultyException e) {
            ErrorMessage message = new ErrorMessage();
            message.setStatus(400);
            message.setErrorMessage("Faculty does not exist");
            message.setDeveloperMessage("Faculty does not exist");
            return new ResponseEntity<ErrorMessage>(message, HttpStatus.valueOf(400));
        }catch (Exception e) {
            ErrorMessage message = new ErrorMessage();
            message.setStatus(400);
            message.setErrorMessage("error creating department");
            message.setDeveloperMessage("error occured while creating");
            return new ResponseEntity<ErrorMessage>(message, HttpStatus.valueOf(400));
        }
    }

    @GetMapping()
    public ResponseEntity<?> getAllDepartment() {
        try {
            Iterable<Department> resultData = departmentService.getAllDepartment();
            Map<String,Iterable<Department>> map = new HashMap<>();
            map.put("departments", resultData);
            return new ResponseEntity<Map<String,Iterable<Department>>>(map, HttpStatus.OK);

        } catch (Exception e) {
            ErrorMessage message = new ErrorMessage();
            message.setDeveloperMessage(e.getMessage());
            message.setErrorMessage("Could not retrieve faculties");
            return new ResponseEntity<ErrorMessage>(message, HttpStatus.valueOf(500));
        }
    }

    @GetMapping("/departments")
    public ResponseEntity<?> getAllByFaculty(Long facultyId) {
        try {
            Iterable<Department> resultData = departmentRepository.findAllByFacultyId(facultyId);
            Map<String,Iterable<Department>> map = new HashMap<>();
            map.put("departments", resultData);
            return new ResponseEntity<Map<String,Iterable<Department>>>(map, HttpStatus.OK);

        } catch (Exception e) {
            ErrorMessage message = new ErrorMessage();
            message.setDeveloperMessage(e.getMessage());
            message.setErrorMessage("Could not retrieve faculties");
            return new ResponseEntity<ErrorMessage>(message, HttpStatus.valueOf(500));
        }
    }
}
