package pl.faferek.educationalapp.views.user;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import pl.faferek.educationalapp.views.MainLayout;
import pl.faferek.educationalapp.views.login.LoginView;

@Route(layout = MainLayout.class)
public class AccountView extends VerticalLayout {



    private final TextField textField;
    public AccountView() {



        textField = new TextField("USER view");
        add(textField);
    }
}
