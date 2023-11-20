package com.example.Prescription.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Prescription.service.UserService;
import com.example.Prescription.web.DTO.userregistration;

@Controller
@RequestMapping("/registration")
public class controller {
    private UserService userService;

    public controller(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") userregistration userregistration) {
        System.out.println(userService.save(userregistration));
        System.out.println("*************************************");
        return "redirect:/registration?success";
    }

    @ModelAttribute("user")
    public userregistration userregistration() {
        return new userregistration(null, null, null, null);
    }

    @GetMapping
    public String showregistrationform() {
        return "registration";
    }

}
