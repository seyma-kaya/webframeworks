package app.rest;

import app.GlobalConfig;
import app.exceptions.NotAcceptableException;
import app.models.User;
import app.security.JWToken;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

    @Autowired
    GlobalConfig globalConfig;

    @PostMapping(path = "login", produces = "application/json")
    public ResponseEntity<User> postAccount(@RequestBody ObjectNode signInInfo) {
        String email = signInInfo.get("email").asText();
        String password = signInInfo.get("password").asText();
        int character = email.indexOf("@");
        String username = "";
        if (character != 1) {
            username = email.substring(0, character);
        }


        if (username.equals(password)) {
            User user = new User(0, username, email, password, "registered_user");
            JWToken jwToken = new JWToken(user.getUsersName(), user.getId(), user.getUserRol());
            String tokenString = jwToken.encode(this.globalConfig.getIssuer(),
                    this.globalConfig.getPassphrase(),
                    this.globalConfig.getTokenDurationOfValidity());

            return ResponseEntity.accepted()
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + tokenString)
                    .body(user);
        } else {
            throw new NotAcceptableException("Cannot authenticate user by email=" + email);
        }



    }
}
