package model;

/**
 * Created by Archibald on 12/4/2016.
 */
public class User {
    private String phone;
    private String password;
    private int status;
    private String emailAddress;

    public User(String phone, String password, int status, String emailAddress) {
        this.phone = phone;
        this.password = password;
        this.status = status;
        this.emailAddress = emailAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
