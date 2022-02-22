package account.controllers;

import account.exception.UserExistException;
import account.models.User;
import account.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder encoder;

    @PostMapping("signup")
    public ResponseEntity<Object> signup(@Valid @RequestBody User user) {
        user.setPassword(encoder.encode(user.getPassword()));


        if (userService.findUserByEmail(user.getEmail().toLowerCase()).isPresent()) {
            throw new UserExistException();
        }
        user.setEmail(user.getEmail().toLowerCase());
        return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
    }

}
