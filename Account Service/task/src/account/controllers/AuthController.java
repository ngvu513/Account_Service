package account.controllers;

import account.model.User;
import account.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    UserService userService;

    @PostMapping("signup")
    public ResponseEntity<User> signup(@Valid @RequestBody User user) {
        return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
    }
}
