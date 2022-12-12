package pl.faferek.educationalapp.views;


import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;
import pl.faferek.educationalapp.views.user.GroupView;
import pl.faferek.educationalapp.views.user.AccountView;


public class MainLayout extends AppLayout {

    public MainLayout() {

        createHeader();
        createDrawer();
    }

    private void createHeader() {
        H1 logo = new H1("EducationalApp");

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo);

        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.setWidth("100%");

        addToNavbar(header);

    }

    private void createDrawer() {
        Accordion accordion = new Accordion();
        //AccordionPanel accordionPanel = new AccordionPanel();


        RouterLink profileLink = new RouterLink("Account", AccountView.class);
        profileLink.setHighlightCondition(HighlightConditions.sameLocation());
        RouterLink groupsLink = new RouterLink("Group", GroupView.class);

        VerticalLayout parentGroups = new VerticalLayout(groupsLink);
        VerticalLayout parentUser = new VerticalLayout(profileLink);
        //accordionPanel.setThemeName("panel");
        accordion.add("My Account", parentUser);
        accordion.add("Groups", parentGroups);

        addToDrawer(new VerticalLayout(accordion));

    }
}
