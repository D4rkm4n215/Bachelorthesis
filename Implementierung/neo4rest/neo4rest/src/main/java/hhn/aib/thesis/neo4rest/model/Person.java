package hhn.aib.thesis.neo4rest.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import hhn.aib.thesis.neo4rest.Views;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Node
public class Person {
    @Id
    @JsonView(Views.Basic.class)
    private String pid;

    @JsonView(Views.Basic.class)
    private String firstname;

    @JsonView(Views.Basic.class)
    private String lastname;

    @JsonView(Views.Basic.class)
    private String email;

    public String getPid() {return pid;}

    public void setPid(String pid) {this.pid = pid;}

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
