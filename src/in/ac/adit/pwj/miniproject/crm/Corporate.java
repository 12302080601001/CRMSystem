package in.ac.adit.pwj.miniproject.crm;

public class Corporate extends Customer {
    private String companyName;

    public Corporate(String id, String name, String email, String companyName) {
        super(id, name, email);
        this.companyName = companyName;
    }

    @Override
    public String getDetails() {
        return super.getDetails() + ", Company: " + companyName;
    }
}