package pl.faferek.educationalapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.faferek.educationalapp.model.UserModel;
import pl.faferek.educationalapp.repository.UserRepo;

@Service
public class UserService {

    private final UserRepo userRepo;
    private UserModel userModel;
    String login;
    String password;


    @Autowired
    public UserService(UserRepo userRepo) {

        this.userRepo = userRepo;
        //this.userModel = userModel;
    }

    public int userCheck(String textFieldLogin, String passwordFieldPassword) {

        boolean check;
        this.login = textFieldLogin;
        this.password = passwordFieldPassword;
        try {
            userModel = userRepo.findUserModelByLogin(login);
            check = userModel.checkPassword(password);
            //System.out.println(userModel.getLogin());
            System.out.println(check);

            if (check && userModel.getLogin().equals(login) && userModel.getRole().equals("user")) {
                return 0;
            } else if (check && userModel.getLogin().equals(login) && userModel.getRole().equals(
                    "admin")) {
                return 1;
            } else {
                return 2;
            }
        } catch (Exception e) {
            return 2;

        }
    }
}
