package org.codex.uom.letmehack.greeting;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by TJR on 2/24/2018.
 */
@RestController
public class GreetingController {
    @GetMapping("/api")
    public ResponseEntity<?> greeting() {
        return new ResponseEntity<Greeting>(new Greeting(),HttpStatus.CREATED);
    }
}
