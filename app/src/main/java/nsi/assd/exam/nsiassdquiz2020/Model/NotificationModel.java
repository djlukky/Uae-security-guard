package nsi.assd.exam.nsiassdquiz2020.Model;

public class NotificationModel {
    private String date;
    private String subject;
    private String message;
    private String url;

    public NotificationModel() {
    }

    public NotificationModel(String date, String subject, String message, String url) {
        this.date = date;
        this.subject = subject;
        this.message = message;
        this.url = url;

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}