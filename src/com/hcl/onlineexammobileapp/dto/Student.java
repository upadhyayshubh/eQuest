
package com.hcl.onlineexammobileapp.dto;


public class Student {
    
    String email;
    String mobile;
    String name;
    String password;
    String college;

    public Student() {
    }

    public Student(String email, String mobile, String name, String password, String college) {
        this.email = email;
        this.mobile = mobile;
        this.name = name;
        this.password = password;
        this.college = college;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }
    @Override
    public String toString() {
    
    	return name;
    }
    
    
    
}
