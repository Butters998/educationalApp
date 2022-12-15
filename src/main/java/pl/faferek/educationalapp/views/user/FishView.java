package pl.faferek.educationalapp.views.user;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route //do dokonczenia dialog z fishkami
public class FishView extends VerticalLayout {

    private TextField imie = new TextField("imie");
    private TextField nazwisko = new TextField("nazwisko");
    private TextField email = new TextField("email");

    Dialog dialog = new Dialog();
    Button button2 = new Button("button2");

    public FishView() {
        Button button = new Button("button");

        add(
                button,
                dialog
        );
        button.addClickListener(e -> showDialog());
    }

    private void showDialog() {
        configureDialog();
        dialog.open();

    }
    private void configureDialog() {
        dialog.add(button2);
        dialog.add(imie, nazwisko, email);
        dialog.setWidth("30em");

    }
}
