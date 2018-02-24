package org.codex.uom.letmehack.departments;

import org.codex.uom.letmehack.faculties.Faculty;
import org.codex.uom.letmehack.faculties.FacultyRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by TJR on 2/24/2018.
 */
@Component
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRespository;
    @Autowired
    private FacultyRespository facultyRespository;

    public Department createDepartment(Department department) {

        //do validations here
        Department existing = departmentRespository.findByName(department.getName());
        if(existing != null){
            throw new DepartmentExistsException();
        }
        Faculty faculty = facultyRespository.findOne(department.getFacultyId());
        if(faculty == null){
            throw new InvalidFacultyException();
        }
        departmentRespository.save(department);
        return department;
    }

    public Iterable<Department> getAllDepartment() {

        //do validations here
        return departmentRespository.findAll();
    }

}
