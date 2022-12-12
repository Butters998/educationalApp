package pl.faferek.educationalapp.dataGenerator;

import org.springframework.stereotype.Service;
import pl.faferek.educationalapp.model.UserModel;
import pl.faferek.educationalapp.repository.UserRepo;

@Service
public class DataGenerator {

    private final UserRepo userRepo;

    public DataGenerator(UserRepo userRepo) {

        this.userRepo = userRepo;
        generateData();
    }


    //@EventListener(ApplicationReadyEvent.class)
     public void generateData() {

        //userRepo.deleteAll();
         UserModel user1 = new UserModel("Anna", "Bugaj", "abugaj@gmail.com", "ABugaj",
                 "Anna123", "user");
         UserModel user2 = new UserModel("Przemysław", "Nowicki", "pnowicki@gmail.com",
                 "PNowicki",
                 "Przemysław123", "user");
         UserModel user3 = new UserModel("Katarzyna", "Nowak", "knowak@wp.pl", "KNowak",
                 "Katarzyna123", "user");
         UserModel user4 = new UserModel("Patryk", "Kowalski", "pkowalski@o2.pl", "PKowalski",
                 "Patryk123", "user");
         UserModel user5 = new UserModel("Kuba", "Lewandowski", "klewandowski@wp.pl",
                 "KLewandowski",
                 "Kuba123", "user");
         UserModel user6 = new UserModel("Joanna", "Nowik", "jnowik@gmail.com", "JNowik",
                 "Joanna123", "user");
         UserModel user7 = new UserModel("Admin", "Admin", "admin@admin.com", "admin",
                 "admin", "admin");
         userRepo.save(user1);
         userRepo.save(user2);
         userRepo.save(user3);
         userRepo.save(user4);
         userRepo.save(user5);
         userRepo.save(user6);
         userRepo.save(user7);
    }

}
