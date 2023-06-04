package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.models.User;
import web.services.UserService;

import java.security.Principal;
@RestController
public class UserRestController {
    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("user/currentUser")
    public ResponseEntity<User> getCurrentUser(Principal principal) {
        User user = userService.findUserByName(principal.getName());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
