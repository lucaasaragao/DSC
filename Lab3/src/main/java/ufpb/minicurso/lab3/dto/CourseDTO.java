package ufpb.minicurso.lab3.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import ufpb.minicurso.lab3.entity.Course;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Data
public class CourseDTO {

    @JsonProperty("nome")
    private String name;
    private Double score;
    private String commentary;
    private Integer like;

    public Course toEntity(){
        return new Course(name,score,commentary, like);
    }
}
