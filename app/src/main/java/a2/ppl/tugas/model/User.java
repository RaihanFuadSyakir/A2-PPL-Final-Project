package a2.ppl.tugas.model;

import java.util.List;

import a2.ppl.tugas.akhir.Location;

import java.util.ArrayList;

public class User {
    private String id;
    private String title;
    private String firstName;
    private String lastName;
    private String gender;
    private String email;
    private String dateOfBirth;
    private String registerDate;
    private String updatedDate;
    private String error;
    private String phone;
    private String picture;
    private Location location;

    public User(String id, String title, String firstName, String lastName, String gender, String email,
            String dateOfBirth, String registerDate, String updatedDate, String error, String phone, String picture,
            Location location) {
        this.id = id;
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.registerDate = registerDate;
        this.updatedDate = updatedDate;
        this.error = error;
        this.phone = phone;
        this.picture = picture;
        this.location = location;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public User(String id, String firstName, String lastName, String email, String registerDate, String updatedDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.registerDate = registerDate;
        this.updatedDate = updatedDate;
    }

    public User() {
    }

    public User(String id, String title, String firstName, String lastName, String gender, String email,
            String dateOfBirth, String registerDate, String phone, String picture, Location location) {
        this.id = id;
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.registerDate = registerDate;
        this.phone = phone;
        this.picture = picture;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<String> compare(User otherUser) {
        List<String> differences = new ArrayList<>();

        // Compare id
        if (!this.id.equals(otherUser.id)) {
            differences.add("id: " + this.id + " (expected) vs " + otherUser.id + " (actual)");
        }

        // Compare firstName
        if (!this.firstName.equals(otherUser.firstName)) {
            differences.add("firstName: " + this.firstName + " (expected) vs " + otherUser.firstName + " (actual)");
        }

        // Compare lastName
        if (!this.lastName.equals(otherUser.lastName)) {
            differences.add("lastName: " + this.lastName + " (expected) vs " + otherUser.lastName + " (actual)");
        }

        // Compare email
        if (!this.email.equals(otherUser.email)) {
            differences.add("email: " + this.email + " (expected) vs " + otherUser.email + " (actual)");
        }

        // Add comparisons for other attributes as needed...

        return differences;
    }

}