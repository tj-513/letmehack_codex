package org.codex.uom.letmehack.faculties;

import org.codex.uom.letmehack.user.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by TJR on 2/24/2018.
 */
public interface FacultyRespository extends CrudRepository<Faculty, Long> {
    Faculty findByName(String name);
}
