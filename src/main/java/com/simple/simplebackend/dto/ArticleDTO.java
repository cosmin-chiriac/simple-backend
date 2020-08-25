package com.simple.simplebackend.dto;

import com.simple.simplebackend.model.Comment;
import com.simple.simplebackend.model.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ArticleDTO {

    private Integer id;

    private String category;

    private String title;

    private String body;

    private User user;

    private List<Comment> articleComments;
}
