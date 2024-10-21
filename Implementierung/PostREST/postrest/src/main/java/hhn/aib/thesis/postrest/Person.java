package hhn.aib.thesis.postrest;

public class Person{
    long id;
    String firstname;
    String lastname;
    String email;

    public Person(DBService db, long pid, String firstname, String lastname, String email) {
        setId(pid);
        setFirstname(firstname);
        setLastname(lastname);
        setEmail(email);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
