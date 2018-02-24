package org.codex.uom.letmehack.faculties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by TJR on 2/24/2018.
 */
@Component
public class FacultyService {
    @Autowired
    private FacultyRespository facultyRespository;

    public Faculty createFaculty(Faculty faculty) {

        //do validations here
        facultyRespository.save(faculty);
        return faculty;
    }

    public Iterable<Faculty> getAllFaculty() {

        //do validations here
        return facultyRespository.findAll();
    }

}
