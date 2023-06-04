package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.models.Role;
import web.models.User;
import web.services.RoleService;
import web.services.UserService;


import java.security.Principal;
import java.util.List;

@RestController
public class AdminsRestController {

    private final UserService userService;

    private final RoleService roleService;

    @Autowired
    public AdminsRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }
    @GetMapping("admin/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("admin/roles")
    public ResponseEntity<List<Role>> getAllRoles() {
        return ResponseEntity.ok(roleService.getAllRoles());
    }

    @GetMapping("admin/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") long id) {
        User user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("admin/currentUser")
    public ResponseEntity<User> getCurrentUser(Principal principal) {
        User user = userService.findUserByName(principal.getName());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("admin/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        userService.saveUser(user);
        return ResponseEntity.ok(user);
    }

    @PatchMapping(value = "admin/users/{id}")
    public ResponseEntity<HttpStatus> updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping(value = "admin/users/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
