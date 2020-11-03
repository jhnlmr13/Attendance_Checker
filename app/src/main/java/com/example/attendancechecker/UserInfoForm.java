package com.example.attendancechecker;

public class UserInfoForm {

  private  String SrCodes,FullName,Email,Password,Gender;

    public UserInfoForm() {





    }

    public UserInfoForm(String srCodes, String fullName, String email, String password, String gender) {
        SrCodes = srCodes;
        FullName = fullName;
        Email = email;
        Password = password;
        Gender = gender;
    }

    public String getSrCodes() {
        return SrCodes;
    }

    public void setSrCodes(String srCodes) {
        SrCodes = srCodes;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }
}
