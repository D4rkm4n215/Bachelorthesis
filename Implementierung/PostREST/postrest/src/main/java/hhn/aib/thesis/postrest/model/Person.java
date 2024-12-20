package hhn.aib.thesis.postrest.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import hhn.aib.thesis.postrest.Views;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Person{
    @Id
    @JsonView(Views.Basic.class)
    private long pid;

    @Column(name = "firstname")
    @JsonView(Views.Basic.class)
    private String firstname;

    @Column(name = "lastname")
    @JsonView(Views.Basic.class)
    private String lastname;

    @Column(name = "email")
    @JsonView(Views.Basic.class)
    private String email;

    public Person() {}

    public Person(String firstname, String lastname, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
