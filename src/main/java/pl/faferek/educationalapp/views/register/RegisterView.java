package pl.faferek.educationalapp.views.register;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionSystemException;
import pl.faferek.educationalapp.model.UserModel;
import pl.faferek.educationalapp.repository.UserRepo;
import pl.faferek.educationalapp.service.UserService;
import pl.faferek.educationalapp.views.login.LoginView;

import java.util.List;

@Route
public class RegisterView extends VerticalLayout {

    private final UserRepo userRepo;
    private final UserService userService;

    private final TextField textFieldName;
    private final TextField textFieldSurname;
    private final TextField textFieldEmail;
    private final TextField textFieldLogin;
    private final PasswordField passwordField;
    private final PasswordField passwordFieldRpt;
    private final Button buttonRegister;
    private final RouterLink backLink;




    @Autowired
    public RegisterView(UserRepo userRepo, UserService userService) {
        this.userRepo = userRepo;
        this.userService = userService;
        textFieldName = new TextField("podaj imię");
        textFieldName.setRequired(true);
        textFieldSurname = new TextField("podaj nazwisko");
        textFieldSurname.setRequired(true);
        textFieldEmail = new TextField("podaj email");
        textFieldEmail.setRequired(true);
        textFieldLogin = new TextField("podaj login");
        textFieldLogin.setRequired(true);
        passwordField = new PasswordField("podaj hasło");
        passwordField.setRequired(true);
        passwordFieldRpt = new PasswordField("powtórz hasło");
        passwordFieldRpt.setRequired(true);
        buttonRegister = new Button("Rejestracja");
        backLink = new RouterLink("Powrót", LoginView.class);



        add(textFieldName,textFieldSurname,textFieldEmail,textFieldLogin,passwordField,
                passwordFieldRpt,buttonRegister,backLink);


        buttonRegister.addClickListener(clickEvent -> addUser());

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);

    }


    public void addUser() {
        List<UserModel> userModelLogin;
        userModelLogin = userRepo.getByLogin(textFieldLogin.getValue());

        List<UserModel> userModelEmail;
        userModelEmail = userRepo.getByEmail(textFieldEmail.getValue());


        if (!checkFields()) {
            showNotification("Uzupełnij wszystkie pola",false);
        }
        else if(passwordField.getValue().equals(passwordFieldRpt.getValue()) && userModelLogin.isEmpty() && userModelEmail.isEmpty()) {
            try {
                UserModel userModel = new UserModel(textFieldName.getValue(),
                        textFieldSurname.getValue(), textFieldEmail.getValue(),
                        textFieldLogin.getValue(), passwordField.getValue());
                        userRepo.save(userModel);
                textFieldName.clear();
                textFieldEmail.clear();
                textFieldSurname.clear();
                textFieldLogin.clear();
                passwordField.clear();
                passwordFieldRpt.clear();
                showNotification("Registered", true);
            } catch (TransactionSystemException e) {
                showNotification("nieprawidłowy email", false);
            }


        } else if (userModelLogin.size() > 0) {
            showNotification("Login już istnieje",false);
        } else if (userModelEmail.size() > 0) {
            showNotification("Email juz istnieje", false);
        } else {
            showNotification("nieprawidłowe hasło!", false);
            passwordField.clear();
            passwordFieldRpt.clear();
        }
    }

    public boolean checkFields() {
        return !textFieldName.isEmpty() && !textFieldSurname.isEmpty() && !textFieldLogin.isEmpty()
                && !textFieldEmail.isEmpty() && !passwordField.isEmpty() && !passwordFieldRpt.isEmpty();
    }
    public void showNotification(String text, boolean success) {
        Notification notification = new Notification();
        notification.setPosition(Notification.Position.MIDDLE);
        notification.setDuration(1000);
        notification.setText(text);
        if (success) {
            notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        }else {
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
        }
        notification.open();
    }

}
