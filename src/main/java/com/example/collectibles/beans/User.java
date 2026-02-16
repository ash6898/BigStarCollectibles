package com.example.collectibles.beans;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
// import jakarta.validation.constraints.Email;
// import jakarta.validation.constraints.NotEmpty;
// import jakarta.validation.constraints.NotNull;
// import jakarta.validation.constraints.Past;
// import jakarta.validation.constraints.Size;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @NotEmpty(message = "Email is required")
    // @Email(message = "Invalid email format")
    private String email;

    // @NotEmpty(message = "Name is required")
    // @Size(min = 1, max = 20, message = "Name must be between 1 and 20 characters")
    private String name;

    // @NotEmpty(message= "Gender is required")
    private String gender;
    private String message;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    // @NotNull(message = "Date of Birth is required")
    // @Past(message = "Date of Birth must be in the past")
    private Date dateOfBirth;
    private boolean newsLetter;
    // @NotEmpty(message = "Favorite Collection is required") 
    private String favoriteCollection;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Date getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public boolean isNewsLetter() {
        return newsLetter;
    }
    public void setNewsLetter(boolean newsLetter) {
        this.newsLetter = newsLetter;
    }
    public String getFavoriteCollection() {
        return favoriteCollection;
    }
    public void setFavoriteCollection(String favoriteCollection) {
        this.favoriteCollection = favoriteCollection;
    }
    
}
