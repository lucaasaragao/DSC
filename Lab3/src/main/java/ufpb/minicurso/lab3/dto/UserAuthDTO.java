package ufpb.minicurso.lab3.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserAuthDTO {
    @NotNull
    private String email;
    private String score;
    private String commentary;

}
