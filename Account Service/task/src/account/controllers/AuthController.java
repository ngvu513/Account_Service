package account.controllers;

import account.model.User;
import account.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    UserService userService;

    @PostMapping("signup")
    public ResponseEntity<Object> signup(@Valid @RequestBody User user) {
        if (userService.findUserByEmail(user.getEmail().toLowerCase()).isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        user.setEmail(user.getEmail().toLowerCase());
        return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
    }

}
