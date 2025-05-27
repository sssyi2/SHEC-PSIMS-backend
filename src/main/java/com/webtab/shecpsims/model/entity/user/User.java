package com.webtab.shecpsims.model.entity.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;


@Data
@ToString
public class User implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer UserID;
    private String UserName;
    @JsonProperty("age")
    private int Age;
    @JsonProperty("gender")
    private String Gender;
@JsonProperty("passwordHash")
    private String PasswordHash;
    @JsonProperty("email")
    private String Email;
    @JsonProperty("phoneNumber")
    private String PhoneNumber;

    private String real_name;
    @JsonProperty("address")
    private String Address;
    @JsonProperty("country")
    private String Country;
    @JsonProperty("area")
    private String Area;

    private String department;

    private int userpoints;

    private String inviteCode;
    private int inviteSum;
    private String City;


    public User() {
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public int getInviteSum() {
        return inviteSum;
    }

    public void setInviteSum(int inviteSum) {
        this.inviteSum = inviteSum;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getPasswordHash() {
        return PasswordHash;
    }

    public void setPasswordHash(String passwordHash) {
        PasswordHash = passwordHash;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String area) {
        Area = area;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getUserpoints() {
        return userpoints;
    }

    public void setUserpoints(int userpoints) {
        this.userpoints = Math.max(0, userpoints);
    }

}
