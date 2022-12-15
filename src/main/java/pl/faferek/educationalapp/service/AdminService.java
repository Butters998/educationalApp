package pl.faferek.educationalapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.faferek.educationalapp.model.UserModel;
import pl.faferek.educationalapp.repository.UserRepo;

import java.util.List;

@Service
public class AdminService {
    private final UserRepo userRepo;
    //private User user;

   @Autowired
    public AdminService(UserRepo userRepo) {
        this.userRepo = userRepo;

    }

    public List<UserModel> findAllUsers(String filterText) {
        if (filterText == null || filterText.isEmpty()) {
            return userRepo.findAll();
        } else {
            return userRepo.search(filterText);
        }
    }

    public long countUsers() {
        return userRepo.count();
    }

    public void deleteUser(UserModel user) {
        userRepo.delete(user);
    }

    public void saveUser(UserModel userModel) {
        if (userModel == null) {
            System.err.println("błąd! User jest pusty");
            return;
        }
        userRepo.save(userModel);

    }












}
