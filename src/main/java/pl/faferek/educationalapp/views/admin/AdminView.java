package pl.faferek.educationalapp.views.admin;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route
public class AdminView extends VerticalLayout {

    private final TextField textField;

    public AdminView() {
        textField = new TextField("ADMIN view");

        add(textField);
    }
}
