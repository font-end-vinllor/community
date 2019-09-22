package life.majiang.community.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "question")
@Setter
@Getter
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "tags")
    private String tags;

    @Column(name = "look")
    private int look;

    @Column(name = "common")
    private int common;

    @Column(name = "gmt_create")
    private Long gmtCreate;

    @Column(name = "gmt_modify")
    private Long gmtModify;


    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "actor_id",referencedColumnName = "user_id")
    @JsonIgnoreProperties(value = "questions")
    @JsonIgnore
    @JSONField(serialize = false)
    private User user;

}
