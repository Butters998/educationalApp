package pl.faferek.educationalapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.faferek.educationalapp.repository.UserRepo;

@Service
public class AdminService {
    private final UserRepo userRepo;
    //private User user;

   @Autowired
    public AdminService(UserRepo userRepo) {
        this.userRepo = userRepo;

    }
}
