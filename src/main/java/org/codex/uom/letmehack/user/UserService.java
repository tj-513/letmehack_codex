package org.codex.uom.letmehack.user;

import org.codex.uom.letmehack.exception.MobileNoException;
import org.codex.uom.letmehack.exception.UserEmailException;
import org.codex.uom.letmehack.exception.UserEmailValidationException;
import org.codex.uom.letmehack.exception.UserRoleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by TJR on 2/24/2018.
 */
@Component
public class UserService {
    @Autowired
    private UserRespository userRespository;

    public User createUser(User user) throws UserEmailException, UserEmailValidationException,
            MobileNoException, UserRoleException {
        if(user.getEmail() != null) {
            User existingUser = userRespository.findOneByEmail(user.getEmail());

        if (existingUser != null) {
            throw new UserEmailException("This user is already exists in the repository.");
        }
        }else{
            throw new UserEmailValidationException("");
        }
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(user.getEmail());
        if (!matcher.matches()) {
            throw new UserEmailValidationException("Email format is wong please re enter.");
        }
        if (user.getMobile() != null) {
            regex = "^((\\+|00)(\\d{1,3})[\\s-]?)?(\\d{10})$";
            Pattern p = Pattern.compile(regex);
            matcher = p.matcher(user.getMobile());
            if (!matcher.matches()) {
                throw new MobileNoException("Mobile no entered is incorrect. Please try again.");
            }
        }
        if (user.getRole() != null) {
            if (!(user.getRole().equals("user") || user.getRole().equals("admin") || user.getRole().equals("moderator"))) {
                throw new UserRoleException("Role is invalid");
            }
        }else {
            user.setRole("user");
        }
        userRespository.save(user);
        return user;
    }

    /*public User getUser(String email){

        //do validations here
        userRespository.findby;
        return user;
    }*/


}
