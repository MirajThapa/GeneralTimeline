package com.example.timeline;

public class Person {
    // PRIVATE VARIABLES ARE MADE TO NOT GIVE ACCESS TO OTHER CLASS DIRECTLY
    private String personId;
    private String firstName;
    private String surname;
    private String emailAddress;
    private String password;
    private String date_of_birth;
    private String phoneNumber;
    private String address;
    private String thumbnail;
    private String registerDate;
    private String updateDate;

    // CONSTRUCTOR OF THIS CLASS WITH ALL PARAMETER VARIABLES
    public Person(String personId, String firstName, String surname, String emailAddress, String password, String date_of_birth, String phoneNumber, String address, String thumbnail, String registerDate,String updateDate) {
        this.personId = personId;
        this.firstName = firstName;
        this.surname = surname;
        this.emailAddress = emailAddress;
        this.password = password;
        this.date_of_birth = date_of_birth;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.thumbnail = thumbnail;
        this.registerDate=registerDate;
        this.updateDate=updateDate;
    }

    // CONSTRUCTOR USED IN REGISTRATION ACTIVITY
    public Person(String firstName, String surname, String emailAddress, String password, String date_of_birth, String phoneNumber, String address, String thumbnail, String registerDate) {
        this.firstName = firstName;
        this.surname = surname;
        this.emailAddress = emailAddress;
        this.password = password;
        this.date_of_birth = date_of_birth;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.thumbnail = thumbnail;
        this.registerDate=registerDate;
    }

    // CONSTRUCTOR USED IN ADMIN ACTIVITY CLASS TO GET THE DETAILS OF THE USER
    public Person(String personId,String firstName,String emailAddress){
        this.firstName = firstName;
        this.emailAddress = emailAddress;
        this.personId = personId;
    }



    public Person(String firstName){
        this.firstName = firstName;
    }

    // GETTERS AND SETTERS OF THE VARIABLES

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
