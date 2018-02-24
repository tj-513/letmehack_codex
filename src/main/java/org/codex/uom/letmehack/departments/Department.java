package org.codex.uom.letmehack.departments;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.codex.uom.letmehack.common.Constants;
import org.codex.uom.letmehack.faculties.Faculty;

import javax.persistence.*;

/**
 * Created by TJR on 2/24/2018.
 */
@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("faculty_id")
    private Long facultyId;

    @Transient
    private String self;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(Long facultyId) {
        this.facultyId = facultyId;
    }

    public String getSelf() {
        return Constants.serverUrl + "/api/departments/" + id;
    }


    public void setSelf(String self) {
        this.self = self;
    }
}
