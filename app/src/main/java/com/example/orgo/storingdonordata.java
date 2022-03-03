package com.example.orgo;

public class storingdonordata {
    String donorid,name,address,district, state, phone,mail,aadhar,s_age, medical_checkup, username;


    public storingdonordata(String donorid, String name, String address, String district, String state, String phone, String mail, String aadhar, String s_age, String medical_checkup, String username) {
        this.donorid = donorid;
        this.name = name;
        this.address = address;
        this.district = district;
        this.state = state;
        this.phone = phone;
        this.mail = mail;
        this.aadhar = aadhar;
        this.s_age = s_age;
        this.medical_checkup = medical_checkup;
        this.username = username;
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

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    public String getMedical_checkup() {
        return medical_checkup;
    }

    public void setMedical_checkup(String medical_checkup) {
        this.medical_checkup = medical_checkup;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
