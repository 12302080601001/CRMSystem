package in.ac.adit.pwj.miniproject.crm;

public class Corporate extends Customer {
    private String companyName;

    public Corporate(String id, String name, String email, String contact, String companyName) {
        super(id, name, email, contact);
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    @Override
    public String toString() {
        return "C," + super.toString() + "," + companyName;
    }
}
