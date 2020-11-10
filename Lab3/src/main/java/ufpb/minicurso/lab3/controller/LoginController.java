package ufpb.minicurso.lab3.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufpb.minicurso.lab3.entity.User;
import ufpb.minicurso.lab3.service.JwtService;
import ufpb.minicurso.lab3.service.impl.UserService;

import javax.servlet.ServletException;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<JSONObject> validateBody(@Valid @RequestBody User user) {
        return jwtValidation(user);
    }

    private ResponseEntity<JSONObject> jwtValidation(User user) {
        return new ResponseEntity<>(jwtService.validate(user), HttpStatus.OK);
    }

    @DeleteMapping("/usuarios/{email}")
    public ResponseEntity<User> removeUser(@PathVariable("email") String email,
                                           @RequestHeader("Authorization") String header) throws ServletException {
        return new ResponseEntity<>(userService.removeUser(email, header), HttpStatus.OK);
    }

}
