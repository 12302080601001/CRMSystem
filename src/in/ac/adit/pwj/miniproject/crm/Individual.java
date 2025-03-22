package in.ac.adit.pwj.miniproject.crm;

public class Individual extends Customer {
    private String personalPhone;

    public Individual(String id, String name, String email, String personalPhone) {
        super(id, name, email);
        this.personalPhone = personalPhone;
    }

    @Override
    public String getDetails() {
        return super.getDetails() + ", Personal Phone: " + personalPhone;
    }
}