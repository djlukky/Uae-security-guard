package nsi.assd.exam.nsiassdquiz2020.Model;

public class ContactModel {
    private String department;
    private String contact;
    private String url;

    public ContactModel() {
    }

    public ContactModel(String department, String contact, String url) {
        this.department = department;
        this.contact = contact;
        this.url = url;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
