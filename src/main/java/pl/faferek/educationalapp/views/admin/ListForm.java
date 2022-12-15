/*
package pl.faferek.educationalapp.views.admin;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import pl.faferek.educationalapp.model.UserModel;
import pl.faferek.educationalapp.repository.UserRepo;
import pl.faferek.educationalapp.service.AdminService;
import pl.faferek.educationalapp.service.UserService;


public class ListForm extends FormLayout {
    TextField name = new TextField("name");
    TextField surname = new TextField("surname");
    TextField login = new TextField("login");
    EmailField emailField = new EmailField("email");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button cancel = new Button("Cancel");

    AdminListView adminListView;
    UserModel userModel;
    AdminService adminService;
    UserRepo userRepo;

    @Autowired
    public ListForm(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public ListForm() {


        addClassName("list-form");

        add(
                name,
                surname,
                login,
                emailField,
                createButtonLayout()
        );

    }


    public Component createButtonLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);

        save.addClickListener(clickEvent -> saveUser());


        return new HorizontalLayout(save, delete, cancel);
    }

    public void saveUser() {


            userModel.setName(name.getValue());
            userModel.setSurname(surname.getValue());
            userModel.setLogin(login.getValue());
            userModel.setEmail(emailField.getValue());
            //adminListView.saveUser(userModel);
            userRepo.save(userModel);
    }

//    private void saveNewUser() {
//        adminListView.createNewUser(login.getValue());
//    }


    public void readDataToForm(UserModel user) {
        this.userModel = user;
        name.setValue(user.getName());
        surname.setValue(user.getSurname());
        login.setValue(user.getLogin());
        emailField.setValue(user.getEmail());
        //String login = userModel.getLogin();


        //this.userModel = userRepo.findUserModelByLogin(login.getValue());

    }

    public void readDataToForm() {
        name.setValue("");
        surname.setValue("");
        login.setValue("");
        emailField.setValue("");
    }

    //EVENTY



}
*/
