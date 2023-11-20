package com.example.Prescription.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.Prescription.model.User;
import com.example.Prescription.web.DTO.userregistration;

public interface UserService extends UserDetailsService{
    User save(userregistration registrationdto);
}
