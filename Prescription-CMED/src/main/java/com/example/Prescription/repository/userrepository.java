package com.example.Prescription.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Prescription.model.User;

@Repository
public interface userrepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
}
