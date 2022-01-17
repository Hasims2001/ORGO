package com.example.orgo;

public class storingdata {
    String name,number,mail,pwd,conpwd;


    public storingdata(String name, String number, String mail, String pwd, String conpwd) {
        this.mail = mail;
        this.name = name;
        this.number = number;
        this.pwd = pwd;
        this.conpwd = conpwd;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getConpwd() {
        return conpwd;
    }

    public void setConpwd(String conpwd) {
        this.conpwd = conpwd;
    }
}
