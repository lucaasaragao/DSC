package ufpb.minicurso.lab3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ufpb.minicurso.lab3.dto.UserDTO;
import ufpb.minicurso.lab3.dto.UserResponseDTO;
import ufpb.minicurso.lab3.entity.User;
import ufpb.minicurso.lab3.service.impl.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> saveUser(@RequestBody User user){
        return new ResponseEntity<>(UserResponseDTO.toDTO(user), HttpStatus.OK);
    }

}
