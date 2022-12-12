package pl.faferek.educationalapp.views.login;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import org.springframework.beans.factory.annotation.Autowired;
import pl.faferek.educationalapp.repository.UserRepo;
import pl.faferek.educationalapp.service.UserService;
import pl.faferek.educationalapp.views.register.RegisterView;
import pl.faferek.educationalapp.views.user.AccountView;
import pl.faferek.educationalapp.views.admin.AdminView;

//@Component
@Route
public class LoginView extends VerticalLayout {

    private final UserService userService;


    private final TextField textFieldLogin;
    private final PasswordField passwordFieldPassword;



    @Autowired
    public LoginView(UserService userService) {
        this.userService = userService;
        final Button buttonLogin;
        final RouterLink registerLink;
        final RouterLink forgotPassLink;

        textFieldLogin = new TextField("Login");
        passwordFieldPassword = new PasswordField("Hasło");
        buttonLogin = new Button("Zaloguj");
        registerLink = new RouterLink("Zarejestruj", RegisterView.class);
        forgotPassLink = new RouterLink("Zapomniałem hasła", RegisterView.class);

        add(textFieldLogin, passwordFieldPassword, buttonLogin, registerLink, forgotPassLink);


        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);


        buttonLogin.addClickListener(clickEvent -> checkUser());
    }

    public void checkUser(){
        int check = userService.userCheck(textFieldLogin.getValue(),
                passwordFieldPassword.getValue());
        if (check == 0) {
            UI.getCurrent().navigate(AccountView.class);
        } else if (check == 1) {
            UI.getCurrent().navigate(AdminView.class);
        } else  {
            showLoginNotification("Złe hasło lub login", false);
        }

    }


    public void showLoginNotification(String text, boolean success) {
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
