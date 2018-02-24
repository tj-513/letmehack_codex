package org.codex.uom.letmehack.user;

import org.codex.uom.letmehack.common.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by TJR on 2/24/2018.
 */
@RestController
@RequestMapping("api/users")
public class UserController {
    @Autowired
    private UserService userService ;
    @PostMapping()
    public ResponseEntity<?> createUser(User user) {
       try {
           user = userService.createUser(user);
           return new ResponseEntity<User>(user, HttpStatus.CREATED);
       }catch (Exception e){
           ErrorMessage message = new ErrorMessage();
           message.setDeveloperMessage("set appropriate messsage here");
           message.setErrorMessage("set appropriate messsage here");
           return new ResponseEntity<ErrorMessage>(message, HttpStatus.valueOf(400));
       }
    }

}
