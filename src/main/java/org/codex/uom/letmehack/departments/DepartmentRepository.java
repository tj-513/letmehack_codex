package org.codex.uom.letmehack.departments;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by TJR on 2/24/2018.
 */
public interface DepartmentRepository extends CrudRepository<Department, Long> {
    Department findByName(String name);
    Iterable<Department> findAllByFacultyId(Long facultyId);
}
