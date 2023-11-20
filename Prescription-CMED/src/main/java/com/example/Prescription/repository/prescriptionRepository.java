package com.example.Prescription.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Prescription.model.prescription;

public interface prescriptionRepository extends JpaRepository<prescription,Long> {
    
}
