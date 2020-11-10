package ufpb.minicurso.lab3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufpb.minicurso.lab3.dto.UserAuthDTO;
import ufpb.minicurso.lab3.entity.Course;
import ufpb.minicurso.lab3.service.JwtService;
import ufpb.minicurso.lab3.service.impl.CourseService;

import javax.servlet.ServletException;
import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/disciplinas")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping()
    public ResponseEntity<List<Course>> getCourses(){
        return new ResponseEntity<>(courseService.getCourse(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourse(@PathVariable("id") long id){
        try{
            return new ResponseEntity<>( courseService.getCourse(id).get(), HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/likes/{id}/")
    public ResponseEntity<Course> setCourseLikes(@PathVariable("id") long id,
                                                 @RequestHeader("Authorization") String header,
                                                 @Valid @RequestBody UserAuthDTO userAuthDTO){
        try {
            return new ResponseEntity<>(courseService.setCourseLike(id, header, userAuthDTO.getEmail()),HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (ServletException e){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping("/nota/{id}/")
    public ResponseEntity<Course> setCourseScore(@PathVariable long id,
                                                 @RequestHeader("Authorization") String header,
                                                 @Valid @RequestBody UserAuthDTO userAuthDTO){

        try {
            return new ResponseEntity<>(courseService.setCourseScore(id, userAuthDTO.getScore(),
                    header, userAuthDTO.getEmail()),HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (ServletException e){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping("/comentarios/{id}")
    public ResponseEntity<Course> setCourseComment(@PathVariable long id,
                                                   @RequestHeader("Authorization") String header,
                                                   @Valid @RequestBody UserAuthDTO userAuthDTO){
        try {
            return new ResponseEntity<>(courseService.setCourseComment(id, userAuthDTO.getCommentary(),
                    header, userAuthDTO.getEmail()), HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (ServletException e){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Course> removeCourse(@PathVariable long id){
        try {
            return new ResponseEntity<>(courseService.removeCourse(id), HttpStatus.OK);
        } catch (ArrayIndexOutOfBoundsException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/ranking/notas")
    public ResponseEntity<List<Course>> sortCoursesByScore(){
        try {
            return new ResponseEntity<>(courseService.sortCoursesByScore(), HttpStatus.OK);
        }catch (ClassCastException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/ranking/likes")
    public ResponseEntity<List<Course>> sortCoursesByLikes(){
        try {
            return new ResponseEntity<>(courseService.sortCoursesByLikes(), HttpStatus.OK);
        } catch (ClassCastException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
