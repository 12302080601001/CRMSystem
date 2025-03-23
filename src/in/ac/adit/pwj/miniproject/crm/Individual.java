package in.ac.adit.pwj.miniproject.crm;

public class Individual extends Customer {
    public Individual(String id, String name, String email, String contact) {
        super(id, name, email, contact);
    }

    @Override
    public String toString() {
        return "I," + super.toString();
    }
}