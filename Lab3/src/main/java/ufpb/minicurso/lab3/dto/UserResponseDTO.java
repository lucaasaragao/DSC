package ufpb.minicurso.lab3.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ufpb.minicurso.lab3.entity.User;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class UserResponseDTO {

    private String name;
    private String email;

    public static UserResponseDTO toDTO(User user){
        return new UserResponseDTO(user.getName(), user.getEmail());
    }

}
