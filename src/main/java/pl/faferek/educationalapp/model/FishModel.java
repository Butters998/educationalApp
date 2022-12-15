package pl.faferek.educationalapp.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class FishModel {

    @Id
    private Long id;
    @ManyToOne
    @JoinColumn(name = "fishgroupmodel_id")
    @NotNull
    private FishGroupModel FishGroupModel;
    @NotNull
    private String question;
    @NotNull
    private String anwser;


}
