package pl.faferek.educationalapp.views.user;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import pl.faferek.educationalapp.views.MainLayout;

@Route (layout = MainLayout.class)
public class GroupView extends VerticalLayout {

    private final TextField textField;

    public GroupView() {

        textField = new TextField("Groups view");
        add(textField);
    }
}
