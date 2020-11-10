package ufpb.minicurso.lab3.exception;

public class UserPasswordNotValid extends RuntimeException {
    public UserPasswordNotValid(String message){
        super(message);
    }
}
