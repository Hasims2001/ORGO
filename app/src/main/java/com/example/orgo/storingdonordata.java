package com.example.orgo;

public class storingdonordata {
    String donorid,name,address,phone,mail,aadhar,s_age;


    public storingdonordata(String donorid, String name, String address, String phone, String mail, String aadhar, String s_age) {
        this.donorid = donorid;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.mail = mail;
        this.aadhar = aadhar;
        this.s_age = s_age;
    }

    public String getDonorid() {
        return donorid;
    }

    public void setDonorid(String donorid) {
        this.donorid = donorid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAadhar() {
        return aadhar;
    }

    public void setAadhar(String aadhar) {
        this.aadhar = aadhar;
    }

    public String getS_age() {
        return s_age;
    }

    public void setS_age(String s_age) {
        this.s_age = s_age;
    }


}
