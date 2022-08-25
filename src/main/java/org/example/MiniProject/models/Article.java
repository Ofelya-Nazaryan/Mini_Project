package org.example.MiniProject.models;

import lombok.*;
import org.example.MiniProject.enums.Gender;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Article {

    public static Article commentedArticle;
    private int id;
    private String title;
    private String content;
    private int user_ID;
    private  User author;
    private List<Comment>comment=new ArrayList<>();




}
