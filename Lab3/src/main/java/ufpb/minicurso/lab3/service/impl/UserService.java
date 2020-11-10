package ufpb.minicurso.lab3.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufpb.minicurso.lab3.entity.User;
import ufpb.minicurso.lab3.repository.UserRepository;
import ufpb.minicurso.lab3.service.JwtService;
import ufpb.minicurso.lab3.service.interfaces.UserServiceInterface;

import javax.servlet.ServletException;
import java.util.Optional;

@Service
public class UserService implements UserServiceInterface {

    @Autowired
    private UserRepository<User, String> userDAO;

    @Autowired
    private JwtService jwtService;

    @Override
    public boolean validateUserPassword(User user) {
        Optional<User> optUser = userDAO.findByEmail(user.getEmail());
        if (optUser.isPresent() && optUser.get().getPassword().equals(user.getPassword()))
            return true;
        return false;
    }

    @Override
    public boolean hasPermission(String authorizationHeader, String email) throws ServletException {
        String subject = jwtService.getTokenSubject(authorizationHeader);
        Optional<User> optUser = userDAO.findByEmail(subject);
        return optUser.isPresent() && optUser.get().getEmail().equals(email);
    }

    @Override
    public User removeUser(String email, String authHeader) throws ServletException {
        User user = findUser(email);
        if (hasPermission(authHeader, email)) {
            userDAO.delete(user);
            return user;
        }
        throw new ServletException("User don't have permission");
    }

    @Override
    public User findUser(String email) {
        Optional<User> user = userDAO.findByEmail(email);
        if(!user.isPresent())
            throw new IllegalArgumentException("User not found");
        return user.get();
    }

    @Override
    public User saveUser(User user) {
        return this.userDAO.save(user);
    }
}
