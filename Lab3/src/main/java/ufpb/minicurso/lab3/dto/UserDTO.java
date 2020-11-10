package ufpb.minicurso.lab3.dto;

import lombok.Data;
import lombok.Getter;
import ufpb.minicurso.lab3.entity.User;

@Data
public class UserDTO {
    private String name;
    private String email;
    private String password;

    public User toEntity(){
        return new User(name,email,password);
    }
}
