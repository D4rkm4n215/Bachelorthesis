package hhn.aib.thesis.postrest;

public class Person{
    long id;
    String name;
    String email;

    public Person(DBService dbService, long pid, String name, String email) {
        setId(pid);
        setName(name);
        setEmail(email);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
