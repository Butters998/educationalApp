package pl.faferek.educationalapp.views.admin;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.apache.catalina.User;
import pl.faferek.educationalapp.model.UserModel;
import pl.faferek.educationalapp.repository.UserRepo;
import pl.faferek.educationalapp.service.AdminService;

@PageTitle("User List")
@Route
public class AdminListView extends VerticalLayout {

    private AdminService adminService;
    private UserRepo userRepo;
    private UserModel userModel;

    TextField name = new TextField("name");
    TextField surname = new TextField("surname");
    TextField login = new TextField("login");
    EmailField emailField = new EmailField("email");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button cancel = new Button("Cancel");

    FormLayout formLayout = new FormLayout();
    Grid<UserModel> grid = new Grid<>(UserModel.class);
    TextField filterText = new TextField();

    //ListForm form;

    public AdminListView(AdminService adminService, UserRepo userRepo, UserModel userModel) {
        this.userRepo = userRepo;
        this.adminService = adminService;
        this.userModel = userModel;
        addClassName("admin-user-list-view");

        setSizeFull();

        configureGrid();
        configureForm();

        add(
                getToolbar(),
                getContent()
        );
        updateList();
        //closeListForm();

    }

//    public void createNewUser(String login) {
//        UserModel user = new UserModel(userRepo.findUserModelByLogin(login));
//
//    }

    public void closeListForm() {
        clearData();
        formLayout.setVisible(false);
        removeClassName("editing");
    }

    public void clearData() {
        name.clear();
        surname.clear();
        login.clear();
        emailField.clear();
    }

    private void readDataToForm(UserModel user) {
        name.setValue(user.getName());
        surname.setValue(user.getSurname());
        login.setValue(user.getLogin());
        emailField.setValue(user.getEmail());
    }


    private void updateList() {
        grid.setItems(adminService.findAllUsers(filterText.getValue()));
    }

    public void saveUser (UserModel user) {
        user.setName(name.getValue());
        user.setSurname(surname.getValue());
        user.setLogin(login.getValue());
        user.setEmail(emailField.getValue());
        adminService.saveUser(user);
        updateList();
        //todo close editor
    }
    public void deleteUser(UserModel user) {
        adminService.deleteUser(user);
        updateList();
        clearData();
    }

    //położenie grida i forma względem siebie
    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, formLayout); //tu po przecinku form

        content.setFlexGrow(3, grid);
        content.setFlexGrow(1, formLayout);
        content.addClassName("content");
        content.setSizeFull();
        return content;
    }

    private void configureForm() {
        //form = new ListForm();
        //form.setWidth("25em");

        name = new TextField("name");
        surname = new TextField("surname");
        login = new TextField("login");
        emailField = new EmailField("email");
        Component[] fields = new Component[]{name, surname, login, emailField};
        formLayout.add(fields);
        formLayout.add(createButtonLayout());

    }
    public Component createButtonLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);

        save.addClickListener(clickEvent -> saveUser(userModel));
        delete.addClickListener(clickEvent -> deleteUser(userModel));
        cancel.addClickListener(clickEvent -> formLayout.setVisible(false));



        return new HorizontalLayout(save, delete, cancel);
    }
    private Component getToolbar() {
        filterText.setPlaceholder("Filter user");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);

        //implementacja żeby grid zmieniał się w trakcie pisania
        filterText.addValueChangeListener(e -> updateList());

        HorizontalLayout toolbar = new HorizontalLayout(filterText);
        toolbar.addClassName("tooblar");
        return toolbar;
    }

    private void configureGrid() {
        grid.addClassName("user-grid");
        grid.setSizeFull();
        grid.setColumns("name", "surname", "email", "login");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(e -> editUser(e.getValue()));
    }

    private void editUser(UserModel user) {
        if (user == null) { clearData();
        } else {
            readDataToForm(user);
            this.userModel = user;
            formLayout.setVisible(true);
            addClassName("editing");
        }
    }



}
