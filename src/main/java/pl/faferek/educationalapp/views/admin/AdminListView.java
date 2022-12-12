package pl.faferek.educationalapp.views.admin;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import pl.faferek.educationalapp.service.AdminService;

@PageTitle("User List")
public class AdminListView extends VerticalLayout {

    private AdminService adminService;

    public AdminListView(AdminService adminService) {
        this.adminService = adminService;
        addClassName("admin-user-list-view");

        setSizeFull();

    }
}
