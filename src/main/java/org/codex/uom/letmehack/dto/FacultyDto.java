package org.codex.uom.letmehack.dto;

import org.codex.uom.letmehack.faculties.Faculty;

public class FacultyDto {

    private Long id;
    private String name;
    private String self;

    public FacultyDto(Faculty faculty) {
        this.setName(faculty.getName());
        this.setId(faculty.getId());
        this.self = "http://localhost:8090/api/faculties/" + this.getId();
    }

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

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }


}
