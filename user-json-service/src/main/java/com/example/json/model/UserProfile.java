package com.example.json.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user_profiles")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phone;
    private String bio;

    // Constructors
    public UserProfile() {}

    public UserProfile(String name, String email, String phone, String bio) {
        this.name  = name;
        this.email = email;
        this.phone = phone;
        this.bio   = bio;
    }
    public Long getId()             { return id; }
    public void setId(Long id)      { this.id = id; }

    public String getName()             { return name; }
    public void setName(String name)    { this.name = name; }

    public String getEmail()              { return email; }
    public void setEmail(String email)    { this.email = email; }

    public String getPhone()              { return phone; }
    public void setPhone(String phone)    { this.phone = phone; }

    public String getBio()            { return bio; }
    public void setBio(String bio)    { this.bio = bio; }
}