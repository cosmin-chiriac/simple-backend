package com.simple.simplebackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Table(name = "users")
public class User implements Serializable {
private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private String email;

    private Integer age;

    @OneToMany(mappedBy = "user")
    @JsonBackReference(value = "article-details")
    private List<Article> articles;

    @OneToMany(mappedBy = "commentAuthor")
    @JsonBackReference(value = "comments-details")
    private List<Comment> comments;
}
