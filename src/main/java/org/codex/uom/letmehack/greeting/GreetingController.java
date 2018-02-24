package org.codex.uom.letmehack.greeting;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by TJR on 2/24/2018.
 */
@RestController
public class GreetingController {
    @CrossOrigin(origins = { "http://localhost:8090" }, allowCredentials = "false")
    @GetMapping("/api")
    public ResponseEntity<?> greeting() {
        return new ResponseEntity<Greeting>(new Greeting(),HttpStatus.OK);
    }




//    @CrossOrigin(origins = { "http://localhost:8090" }, allowCredentials = "false")
//    @RequestMapping(value = "/api/users", method = RequestMethod.POST)
//    @ResponseStatus(HttpStatus.OK)
//    public User getUser(@PathVariable("id") Long id) {
//        return userRepository.findOne(id);
//    }




}
