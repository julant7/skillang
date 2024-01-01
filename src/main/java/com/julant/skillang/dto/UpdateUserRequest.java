package com.julant.skillang.dto;

public class UpdateUserRequest {
    private String firstName;
    private String lastName;
    private String sex;
    private Long dateOfBirth;
    private  String bio;

    public UpdateUserRequest() {

    }
    private UpdateUserRequest(String firstName, String lastName, String sex, Long dateOfBirth, String bio) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
        this.bio = bio;
    }

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getSex() {
        return sex;
    }
    public Long getDateOfBirth() {
        return dateOfBirth;
    }
    public String getBio() {
        return bio;
    }
}
