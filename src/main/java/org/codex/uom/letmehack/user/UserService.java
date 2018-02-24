package org.codex.uom.letmehack.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by TJR on 2/24/2018.
 */
@Component
public class UserService {
    @Autowired
    private UserRespository userRespository;

    public User createUser(User user){

        //do validations here
        userRespository.save(user);
        return user;
    }
}
