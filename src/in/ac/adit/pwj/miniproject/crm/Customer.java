package in.ac.adit.pwj.miniproject.crm;

public abstract class Customer {
    protected String id;
    protected String name;
    protected String email;
    protected String contact;

    public Customer(String id, String name, String email, String contact) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.contact = contact;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getContact() { return contact; }

    @Override
    public String toString() {
        return id + "," + name + "," + email + "," + contact;
    }
}