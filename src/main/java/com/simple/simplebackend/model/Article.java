package com.simple.simplebackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.w3c.dom.ls.LSInput;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Table(name = "articles")
public class Article implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank
    private String category;

    @NotBlank
    private String title;

    @Lob
    private String body;

    @ManyToOne ()
    @JoinColumn (name = "user_id")
    @JsonManagedReference(value = "article-details")
    private User user;

    @OneToMany (mappedBy = "referredArticle")
    private List<Comment> articleComments;


}
