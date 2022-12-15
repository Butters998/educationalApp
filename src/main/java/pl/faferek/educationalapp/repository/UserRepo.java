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

    @Query(nativeQuery = true, value = "SELECT * FROM USER_MODEL WHERE USER_MODEL.LOGIN =:login")
    List<UserModel> getByLogin (@Param("login") String login);

    @Query(nativeQuery = true, value = "SELECT * FROM USER_MODEL WHERE USER_MODEL.EMAIL =:email")
    List<UserModel> getByEmail (@Param("email") String email);

    @Query("select u from UserModel u " +
            "where lower(u.name) like lower(concat('%', :searchTerm, '%'))"+
            "or lower(u.surname) like lower(concat('%', :searchTerm, '%'))"+
            "or lower(u.login) like lower(concat('%', :searchTerm, '%'))"+
            "or lower(u.email) like lower(concat('%', :searchTerm, '%'))")
    List<UserModel> search(@Param("searchTerm") String searchTerm);
}
