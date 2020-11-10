package ufpb.minicurso.lab3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ufpb.minicurso.lab3.entity.User;

import java.io.Serializable;
import java.util.Optional;

@Repository
public interface UserRepository<T, ID extends Serializable> extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);
}
