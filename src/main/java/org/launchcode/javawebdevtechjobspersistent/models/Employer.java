package org.launchcode.javawebdevtechjobspersistent.models;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Employer extends AbstractEntity {

    //not sure if I need this
    //do I need getters and setters for name here
    @NotBlank(message = "name is required")
    @Size(min = 3, max = 100, message = "name must be between 3 and 100 characters")
    private String name;

    @NotNull
    @NotBlank(message = "name is required")
    @Size(min = 3, max = 100, message = "location must be between 3 and 100 characters")
    private String location;

    public Employer(String location, String name) {
        super();
        this.location = location;
        this.name = name;
    }

    public Employer() {}

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
