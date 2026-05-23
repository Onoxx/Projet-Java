package MVC.Model;

public class User {
    private int id;
    private String name;
    private String email;

    public User(Integer id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getEmail() {return email;}
    public String getName() {return name;}
    public int getId() {return id;}
    @Override
    public String toString() {
        return name;
    }
}
