package pl.faferek.educationalapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.faferek.educationalapp.model.UserModel;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<UserModel, Long> {
    //List<UserModel> findUserModelByLogin(String login);

    UserModel findUserModelByLogin(String login);

    @Query(nativeQuery = true, value = "SELECT * FROM USERMODEL WHERE USERMODEL.LOGIN =:login")
    List<UserModel> getByLogin (@Param("login") String login);

    @Query(nativeQuery = true, value = "SELECT * FROM USERMODEL WHERE USERMODEL.EMAIL =:email")
    List<UserModel> getByEmail (@Param("email") String email);

}
