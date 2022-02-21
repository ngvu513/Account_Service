package account.controllers;

import account.model.User;
import account.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class PaymentController {
    @Autowired
    UserService userService;

    @GetMapping("empl/payment")
    public ResponseEntity<User> getEmployPayroll(@AuthenticationPrincipal UserDetails details) {
        Optional<User> user = userService.findUserByEmail(details.getUsername());
        return user.map(value -> new ResponseEntity<>(value, HttpStatus.ACCEPTED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.UNAUTHORIZED));
    }

}
