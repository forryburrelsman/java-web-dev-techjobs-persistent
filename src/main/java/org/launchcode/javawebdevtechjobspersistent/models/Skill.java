package org.launchcode.javawebdevtechjobspersistent.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Skill extends AbstractEntity {

    @NotBlank(message = "description is required")
    @Size(min = 3, max = 200, message = "location must be between 3 and 200 characters")
    private String description;

    @NotNull
    @ManyToMany(mappedBy="skills")
    private final List<Job> jobs = new ArrayList<>();

    public Skill(String description){
        super();
        this.description = description;
    }

    public Skill() {}

    public List<Job> getJobs() {return jobs;}

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }
}