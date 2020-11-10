package ufpb.minicurso.lab3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ufpb.minicurso.lab3.entity.Course;

import java.io.Serializable;

@Repository
public interface CourseRepository<T, ID extends Serializable> extends JpaRepository<Course, Long> {
}
