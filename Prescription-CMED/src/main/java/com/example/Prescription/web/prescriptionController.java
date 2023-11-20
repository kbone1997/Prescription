package com.example.Prescription.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.Prescription.model.prescription;
import com.example.Prescription.repository.prescriptionRepository;

@Controller
public class prescriptionController {

    @Autowired
    private prescriptionRepository prescriptionRepository;

    @GetMapping("/prescription")
    public String showPrescription(Model model) {
        someMethod();
        model.addAttribute("formPrescription", new prescription(null, null, 0, null, null, null));
        return "prescription";
    }

    @PostMapping("/prescription")
    public String submitPrescription(prescription data) {
        prescriptionRepository.save(data);
        return "redirect:/prescription?success";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    public void someMethod() {
        // Get the current authentication
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Check if the user is authenticated
        if (authentication != null && authentication.isAuthenticated()) {
            // User is authenticated
            String username = authentication.getName();
            // Perform actions for authenticated user
            System.out.println("User " + username + " is authenticated.");
        } else {
            // User is not authenticated
            System.out.println("User is not authenticated.");
        }
    }

    @GetMapping("/prescriptionList")
    public String showPrescriptionsList(Model model) {
        // Fetch all prescriptions from the database
        List<prescription> prescriptions = prescriptionRepository.findAll();

        // Add the list to the model
        model.addAttribute("prescriptions", prescriptions);

        return "prescriptionList";
    }
}
