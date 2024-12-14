package hhn.aib.thesis.postrest.DTO;

import jakarta.persistence.*;


public class PersonDTO {

    @Id
    private long pid;

    private String firstname;

    private String lastname;

    private String email;

    public PersonDTO() {}

    public PersonDTO(long pid, String firstname, String lastname, String email) {
        this.pid = pid;
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
