package org.launchcode.javawebdevtechjobspersistent.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Employer extends AbstractEntity {

    @NotNull
    @Size(min = 3, max = 100, message = "location must be between 3 and 100 characters")
    private String location;

    //Use the @OneToMany and @JoinColumn annotations on the jobs list in Employer
    // to declare the relationship between data tables.
    @OneToMany //(mappedBy = "employer")
    @JoinColumn(name = "employer_id")
    private final List<Job> jobs = new ArrayList<>();

    public Employer(String location) {
        super();
        this.location = location;
    }

    public Employer() {}

    public List<Job> getJobs() { return jobs; }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
