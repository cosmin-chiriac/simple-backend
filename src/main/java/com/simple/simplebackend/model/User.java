package com.simple.simplebackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    /* List of the article types the user is subscribed to. Has to match the values in ArticleTypeEnum*/
    @ElementCollection
    private List<String> subscriptions;

    @OneToMany(mappedBy = "user")
    @JsonBackReference(value = "article-details")
    private List<Article> articles;

    @OneToMany(mappedBy = "user")
    @JsonBackReference(value = "comments-details")
    private List<Comment> comments;
}
