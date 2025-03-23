package in.ac.adit.pwj.miniproject.crm;

public class Corporate extends Customer {
    public Corporate(String id, String name, String email, String contact) {
        super(id, name, email, contact);
    }

    @Override
    public String toString() {
        return "C," + super.toString();
    }
}