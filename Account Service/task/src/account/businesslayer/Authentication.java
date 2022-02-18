package account.businesslayer;

import account.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class Authentication {
    @PostMapping("signup")
    public ResponseEntity<User> signup() {
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }


}
