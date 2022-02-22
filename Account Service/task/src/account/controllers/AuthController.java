package account.controllers;

import account.exception.BreachedPasswordException;
import account.exception.MinPasswordException;
import account.exception.SamePasswordException;
import account.exception.UserExistException;
import account.models.NewPassword;
import account.models.User;
import account.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder encoder;

    List<String> breachedPasswords = List.of("PasswordForJanuary", "PasswordForFebruary", "PasswordForMarch", "PasswordForApril",
            "PasswordForMay", "PasswordForJune", "PasswordForJuly", "PasswordForAugust",
            "PasswordForSeptember", "PasswordForOctober", "PasswordForNovember", "PasswordForDecember");

    @PostMapping("signup")
    public ResponseEntity<Object> signup(@Valid @RequestBody User user) {

        if (breachedPasswords.contains(user.getPassword())) {
            throw new BreachedPasswordException();
        }
        if (user.getPassword().length() < 12) {
            throw new MinPasswordException();
        }
        user.setPassword(encoder.encode(user.getPassword()));
        if (userService.findUserByEmail(user.getEmail().toLowerCase()).isPresent()) {
            throw new UserExistException();
        }

        user.setEmail(user.getEmail().toLowerCase());
        return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
    }

    @PostMapping("changepass")
    public ResponseEntity<Object> changePassword(@Valid @RequestBody NewPassword newPasswordMap, @AuthenticationPrincipal UserDetails details) {
        if (details != null) {
            Optional<User> userOptional = userService.findUserByEmail(details.getUsername());
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                String newPassword = newPasswordMap.getNew_password();
                if (breachedPasswords.contains(newPassword)) {
                    throw new BreachedPasswordException();
                }
                if (encoder.matches(newPassword, user.getPassword())){
                    throw new SamePasswordException();
                }
                if (newPassword.length() < 12) {
                    throw new MinPasswordException();
                }
                user.setPassword(encoder.encode(newPassword));
                userService.save(user);
                Map<String, String> responseJson = new HashMap<>();
//                responseJson.put("newPassword", newPassword);
//                responseJson.put("user.getPassword())", user.getPassword());
                responseJson.put("email", user.getEmail());
                responseJson.put("status", "The password has been updated successfully");
                return new ResponseEntity<>(responseJson, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
