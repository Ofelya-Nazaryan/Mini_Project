package org.example.MiniProject;

import lombok.SneakyThrows;
import org.example.MiniProject.enums.Gender;
import org.example.MiniProject.manager.ArticleManager;
import org.example.MiniProject.manager.CommentManager;
import org.example.MiniProject.manager.UserManager;
import org.example.MiniProject.models.Article;
import org.example.MiniProject.models.Comment;
import org.example.MiniProject.models.User;
import org.example.MiniProject.util.Md5Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {
    private User currentUser;
    private Scanner scanner = new Scanner(System.in);
    private UserManager userManager = new UserManager();
    private ArticleManager articleManager = new ArticleManager();
     private CommentManager commentManager=new CommentManager();

    public void start() {
        boolean normalCommand = false;
        while (!normalCommand) {
            Command.home();
            String command = scanner.nextLine();
            switch (command) {
                case "1" -> {
                    normalCommand = true;
                    login();
                }
                case "2" -> {
                    normalCommand = true;
                    registration();
                }
                default -> {
                    System.out.println("Pleas press only 1 or 2");
                }
            }
        }

    }

    @SneakyThrows
    private void registration() {
        System.out.println("Registration process");

        boolean emailCorrect = false;
        String email = "";
        while (!emailCorrect) {
            System.out.println("Press email");
            email = scanner.nextLine();
            emailCorrect = !userManager.existByEmail(email);
            if (!emailCorrect) {
                System.out.printf("User with email = %s already exists%n", email);
            }
        }


        System.out.println("Press name");
        String name = scanner.nextLine();

        System.out.println("Press surname");
        String surname = scanner.nextLine();

        System.out.println("Press password");
        String password = Md5Util.toHash(scanner.nextLine());

        System.out.println("Press age");
        int age = Integer.parseInt(scanner.nextLine());

        System.out.println("Press gender");
        Gender gender = Gender.valueOf(scanner.nextLine());

        System.out.println("Press phone number");
        String phoneNumber = scanner.nextLine();

        currentUser = userManager.save(User.builder()
                .name(name)
                .surname(surname)
                .email(email)
                .password(password)
                .phoneNumber(phoneNumber)
                .age(age)
                .gender(gender)
                .build());
        System.out.println("Successfully registered");
        userHome();

    }

    private void login() {

        System.out.println("*** Login precess ***");
        System.out.println("Press email");

        String email = scanner.nextLine();

        System.out.println("Press password");
        String password = scanner.nextLine();
        User byEmail = userManager.getByEmail(email);
        if (byEmail != null && Md5Util.match(password, byEmail.getPassword())) {
            currentUser = byEmail;
            userHome();
        } else {
            System.out.println("Wrong email or password");
            start();
        }


    }

    private void userHome() {
        boolean normalCommand = false;
        while (!normalCommand) {
            Command.userHome();
            String command = scanner.nextLine();
            switch (command) {
                case "1" -> {
                    normalCommand = true;
                    allArticles();
                }
                case "2" -> {
                    normalCommand = true;
                    userArticles();
                }
                case "3" -> {
                    normalCommand = true;
                    addArticle();
                }
                case "4" -> {
                    normalCommand = true;
                    logOut();
                }
                case "5" -> {
                    normalCommand = true;
                    addComment();
                }

                default -> System.out.println("Press only {1,2,3,4,5} numbers");
            }
        }
    }

    private void allArticles() {
        List<Article> all = articleManager.all();
        for (Article article : all) {
            System.out.printf("Article: id= %s | title =%s | content = %s | Author_Name = %s | Author_Surname = %s%n",
                    article.getId(), article.getTitle(), article.getContent(),
                    article.getAuthor().getName(), article.getAuthor().getSurname());
            if (!article.getComment().isEmpty()) {
                System.out.println("comments");
                for (Comment comment : article.getComment()) {
                    System.out.println(comment);
                }
            }

        }
    }

    private void userArticles() {

        try {
            List<Article> myArticles = articleManager.articlesByAuthor(currentUser);
            for (Article article : myArticles) {
                System.out.printf("Articles of author: id= %s | title =%s | content = %s ",
                        article.getId(), article.getTitle(), article.getContent());
                List<Comment> myComments = commentManager.commentsByArticle(article);
                if (!article.getComment().isEmpty()) {
                    System.out.println("comments");
                    for (Comment comment : article.getComment()) {
                        System.out.println("     * ");
                    }
                }
            }
        }
        catch(NullPointerException e){
            e.getStackTrace();}
         }



    private void addArticle() {
        System.out.println("Please insert title");
        String title = scanner.nextLine();
        System.out.println("Please insert content");
        String content = scanner.nextLine();
        Article newArticle = articleManager.save(Article.builder()
                .title(title)
                .content(content)
                .user_ID(currentUser.getId())
                .build());
    }



    private void logOut() {
      System.exit(0);
           }



    private void addComment() {//todo
        allArticles();
        System.out.println("Choose article by inserting articleID");
        String commentedArticle = scanner.nextLine();
        System.out.println("Insert comment");
        String myComment = scanner.nextLine();
        Comment comment = new Comment(myComment);
        comment.setUser_ID(currentUser.getId());
        comment.setContent(myComment);
         commentManager.save(comment, articleManager.filterbyID(Integer.parseInt(commentedArticle)));
        System.out.println();

    }






}