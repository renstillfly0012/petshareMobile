package com.example.petshare;

public class RegisterRequest {

    private String fName;
    private String Email;
    private String password;
    private String confirm;

    public void getAll(String name, String email, String password, String confirm){
        this.fName = name;
        this.Email = email;
        this.password = password;
        this.confirm = confirm;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }
}
