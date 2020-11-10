package ufpb.minicurso.lab3.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufpb.minicurso.lab3.entity.User;
import ufpb.minicurso.lab3.exception.UserPasswordNotValid;
import ufpb.minicurso.lab3.filter.TokenFilter;
import ufpb.minicurso.lab3.service.impl.CourseService;
import ufpb.minicurso.lab3.service.impl.UserService;

import java.security.SignatureException;
import java.util.Date;

@Service
public class JwtService {
    @Autowired
    UserService userService;
    public static final String TOKEN_KEY = "cryptic writings";

    public JSONObject validate(User user){
        JSONObject tokenJSON = new JSONObject();

        if (!userService.validateUserPassword(user)) {
            throw new UserPasswordNotValid("User or password not valid. Not possible to sign in.");
        }

        String token = tokenGenerator(user.getEmail());

        tokenJSON.put("token",token);
        return tokenJSON;
    }

    private String tokenGenerator(String email) {
        return Jwts.builder().setHeaderParam("typ", "JWT").setSubject(email)
                .signWith(SignatureAlgorithm.HS512, TOKEN_KEY)
                .setExpiration(new Date(System.currentTimeMillis() + 3 * 60 * 1000)).compact();
    }


    public String getTokenSubject(String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new SecurityException("Token not found or malformed!");
        }

        String token = authorizationHeader.substring(TokenFilter.TOKEN_INDEX);

        String subject = null;
        subject = Jwts.parser().setSigningKey(TOKEN_KEY).parseClaimsJws(token).getBody().getSubject();
        return subject;
    }

}
