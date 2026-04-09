package com.example.json.controller;

import com.example.json.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserController extends JpaRepository<UserProfile, Long> {
}