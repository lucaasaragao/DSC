package ufpb.minicurso.lab3.service.interfaces;

import ufpb.minicurso.lab3.entity.Course;

import javax.servlet.ServletException;
import java.util.List;
import java.util.Optional;

public interface CourseServiceInterface {

    List<Course> getCourse();

    Optional<Course> getCourse(long id);

    Course setCourseLike(long id, String header, String email) throws ServletException;

    Course setCourseScore(long id, String scoreField, String header, String email) throws ServletException;

    Course setCourseComment(long id, String comment, String header, String email) throws ServletException;

    Course removeCourse(long id);

    List<Course> sortCoursesByScore();

    List<Course> sortCoursesByLikes();
}
