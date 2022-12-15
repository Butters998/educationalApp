package pl.faferek.educationalapp.model;

import javax.annotation.Nullable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Entity
public class FishGroupModel {
    @Id
    private Long id;
    @NotNull
    private String name;
    @OneToMany(mappedBy = "fishgroupmodel")
    @Nullable
    private List<FishModel> fishModels = new LinkedList<>();
}
