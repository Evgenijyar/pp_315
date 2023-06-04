package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.models.User;
import web.services.UserService;
import web.util.UserValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final UserValidator userValidator;
    private final UserService userService;

    @Autowired
    public AuthController(UserValidator userValidator,
                          UserService userService) {
        this.userValidator = userValidator;
        this.userService = userService;
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "auth/login";
    }

    @GetMapping("/registration")
    public String getRegistrationPage() {
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String saveRegistrationForm(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "auth/registration";
        }
        userService.saveUser(user);
        return "redirect:auth/login";
    }
}
