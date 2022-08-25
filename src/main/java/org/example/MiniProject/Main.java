package org.example.MiniProject;

import org.example.MiniProject.enums.Gender;
import org.example.MiniProject.manager.ArticleManager;
import org.example.MiniProject.manager.UserManager;
import org.example.MiniProject.models.Article;
import org.example.MiniProject.models.User;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Application application = new Application();
        application.start();


        UserManager userManager = new UserManager();
//        User anna = userManager.save(User.builder()
//                .name("Anna")
//                .surname("Petrosyan")
//                .email("ann.petrosyan@gmail.com")
//                .password("123456")
//                .age(18)
//                .avatar("avatar.png")
//                .gender(Gender.FEMALE)
//                .phoneNumber("+374549474")
//                .build());


//        boolean isDelete = userManager.deleteById(4);
//        System.out.println(isDelete);


//     User anna = userManager.getByEmailAndPassword("ann.petrosyan@gmail.com", "123456");
//         System.out.println(anna.toString());

//        User ofelya = userManager.getByEmailAndPassword("ofelya.nazaryan7777@gmail.com", "19967777");
//        ofelya.setAge(81);
//        System.out.println(userManager.update(1, ofelya));








    }


}
