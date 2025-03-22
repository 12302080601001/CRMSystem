package in.ac.adit.pwj.miniproject.crm;

public class Customer {
    protected String id;
    protected String name;
    protected String email;

    public Customer(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getDetails() {
        return "ID: " + id + ", Name: " + name + ", Email: " + email;
    }
}