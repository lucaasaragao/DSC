package ufpb.minicurso.lab3.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufpb.minicurso.lab3.entity.Course;
import ufpb.minicurso.lab3.repository.CourseRepository;
import ufpb.minicurso.lab3.service.interfaces.CourseServiceInterface;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
public class CourseService implements CourseServiceInterface {


    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserService userService;

    private List<Course> course;

    public CourseService(){}

    @PostConstruct
    public void initCourse(){
        if(courseRepository.count() == 96){
            return;
        } else{
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<Course>> typeReference = new TypeReference<List<Course>>(){};
            InputStream inputStream = ObjectMapper.class.getResourceAsStream("/json/courses.json");
            try{
                course = mapper.readValue(inputStream, typeReference);
                this.courseRepository.saveAll(course);
                System.out.println("Courses saved on Db");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Course> getCourse(){
        return courseRepository.findAll();
    }

    @Override
    public Optional<Course> getCourse(long id){
        return courseRepository.findById(id);
    }

    @Override
    public Course setCourseLike(long id, String header, String email) throws ServletException {
        if(getCourse(id).isEmpty()) throw new NoSuchElementException();

        if(userService.hasPermission(header, email)){
            int numberOfLikes = getCourse(id).get().getLikes();
            getCourse(id).get().setLikes(numberOfLikes+1);
            courseRepository.save(getCourse(id).get());
            return getCourse(id).get();
        }
        return null;
    }

//    TODO: Tratar caso usuário envie nota inválida
    @Override
    public Course setCourseScore(long id,String scoreField, String header, String email)
            throws ServletException {
        if(getCourse(id).isEmpty()) throw new NoSuchElementException();

        if(userService.hasPermission(header, email)){
            double score = (double) JSONValue.parse(scoreField);
            getCourse(id).get().setScore(score);
            courseRepository.save(getCourse(id).get());
            return getCourse(id).get();
        }
        return null;
    }

    @Override
    public Course setCourseComment(long id, String comment, String header, String email)
            throws ServletException {
        if(getCourse(id).isEmpty()) throw new NoSuchElementException();

        if(userService.hasPermission(header, email)){
            String commentary = comment;
            String oldCommentaries = getCourse(id).get().getCommentary();

            if(oldCommentaries == null){
                getCourse(id).get().setCommentary(commentary);
            } else {
                getCourse(id).get().setCommentary(oldCommentaries + System.lineSeparator() + commentary);
            }
            courseRepository.save(getCourse(id).get());
            return getCourse(id).get();
        }
        return null;
    }

    @Override
    public Course removeCourse(long id){
        if(courseRepository.findById(id).isEmpty()) throw new ArrayIndexOutOfBoundsException();

        Course deletedCourse = getCourse(id).get();
        courseRepository.deleteById(id);

        return deletedCourse;
    }

    @Override
    public List<Course> sortCoursesByScore(){
        return sortCourses("score");
    }

    @Override
    public List<Course> sortCoursesByLikes(){
        return sortCourses("likes");
    }

    private List<Course> sortCourses(String mode) {
        course = getCourse();
        Collections.sort(course, (o1, o2) -> {

            if(mode.equals("score")){
                if(o1.getScore() > o2.getScore()) return -1;
                if(o1.getScore() < o2.getScore()) return 1;
            } else if(mode.equals("likes")) {
                if(o1.getLikes() > o2.getLikes()) return -1;
                if(o1.getLikes() < o2.getLikes()) return 1;
            }

            return 0;
        });
        return course;
    }
}
