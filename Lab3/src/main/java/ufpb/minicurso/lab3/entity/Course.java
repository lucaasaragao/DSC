package ufpb.minicurso.lab3.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Course {
    @Id @GeneratedValue
    private long id;
    @JsonProperty("nome")
    private String name;
    private double score;

    private String commentary;
    private int likes;

    public Course(String name, double score, String commentary, int likes){
        this.name = name;
        this.score = score;
        this.commentary = commentary;
        this.likes = likes;
    }
}
