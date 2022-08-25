package org.example.MiniProject.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.MiniProject.enums.Gender;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor


public class Comment {

    private int id;
    private String content;
    private int user_ID;
    private  User author;


    public Comment(String myComment) {

    }
}

