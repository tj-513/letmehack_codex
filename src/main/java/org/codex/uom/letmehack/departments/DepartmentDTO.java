package org.codex.uom.letmehack.departments;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.codex.uom.letmehack.faculties.Faculty;
import org.codex.uom.letmehack.faculties.FacultyRespository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by TJR on 2/25/2018.
 */
@JsonPropertyOrder({ "self", "name", "faculty" })
public class DepartmentDTO extends Department{


    private Faculty faculty;
    public DepartmentDTO(Department department) {
        this.setName(department.getName());
        this.setSelf(department.getSelf());
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }
}
