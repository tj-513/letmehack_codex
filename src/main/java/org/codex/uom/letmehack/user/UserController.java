package org.codex.uom.letmehack.user;

import javassist.bytecode.stackmap.BasicBlock;
import org.codex.uom.letmehack.common.ErrorMessage;
import org.codex.uom.letmehack.dto.UserDto;
import org.codex.uom.letmehack.exception.MobileNoException;
import org.codex.uom.letmehack.exception.UserEmailException;
import org.codex.uom.letmehack.exception.UserEmailValidationException;
import org.codex.uom.letmehack.exception.UserRoleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by TJR on 2/24/2018.
 */
@RestController
@RequestMapping("api/users")
public class UserController {
    @Autowired
    private UserService userService ;
    @PostMapping()
    public ResponseEntity<?> createUser(@RequestBody User user) {
       try {
           user = userService.createUser(user);
           UserDto userDto = new UserDto(user);
           return new ResponseEntity<UserDto>(userDto, HttpStatus.CREATED);
       }catch (UserEmailException ex){
           ErrorMessage message = new ErrorMessage();
           message.setStatus(409);
           message.setDeveloperMessage("User creation failed because the email: " + user.getEmail() + " already exists");
           message.setErrorMessage("A user with email: "+user.getEmail()+" already exists");
           return new ResponseEntity<ErrorMessage>(message, HttpStatus.valueOf(409));
       }catch (UserEmailValidationException e){
           ErrorMessage message = new ErrorMessage();
           message.setStatus(400);
           message.setDeveloperMessage("Please check with a different email.");
           message.setErrorMessage("The current email is wrong.");
           return new ResponseEntity<ErrorMessage>(message, HttpStatus.valueOf(400));
       }catch (MobileNoException e){
           ErrorMessage message = new ErrorMessage();
           message.setStatus(400);
           message.setDeveloperMessage("Please check with a different mobile number.");
           message.setErrorMessage("The current mobile is wrong.");
           return new ResponseEntity<ErrorMessage>(message, HttpStatus.valueOf(400));
       }catch (UserRoleException e){
           ErrorMessage message = new ErrorMessage();
           message.setStatus(400);
           message.setDeveloperMessage("User roll sould either be null or user,default,admin.");
           message.setErrorMessage("The current mobile is wrong.");
           return new ResponseEntity<ErrorMessage>(message, HttpStatus.valueOf(400));
       }
    }

    /*@GetMapping("/")
    public List<Note> getAllNotes() {
        return n;
    }*/

}
