package ufpb.minicurso.lab3.service.interfaces;

import ufpb.minicurso.lab3.entity.User;

import javax.servlet.ServletException;

public interface UserServiceInterface {

    boolean validateUserPassword(User user);

    boolean hasPermission(String authorizationHeader, String email) throws ServletException;

    User removeUser(String email, String authHeader) throws ServletException;

    User findUser(String email);

    User saveUser(User user);
}
