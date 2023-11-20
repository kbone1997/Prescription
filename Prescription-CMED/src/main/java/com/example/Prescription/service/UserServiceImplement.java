package com.example.Prescription.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.Prescription.model.User;
import com.example.Prescription.model.Role;
import com.example.Prescription.repository.userrepository;
import com.example.Prescription.web.DTO.userregistration;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImplement implements UserService {
    private userrepository userrepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserServiceImplement(userrepository userrepository) {
        this.userrepository = userrepository;
    }

    @Override
    public User save(userregistration registrationdto) {
        User user = new User(registrationdto.getFirstname(), registrationdto.getLastname(), registrationdto.getEmail(),
                passwordEncoder.encode(registrationdto.getPassword()), Arrays.asList(new Role("ROLE_USER")));
        return userrepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("This is the email:" + username + "*************************************************");
        User user = userrepository.findByEmail(username);
        System.out.println(user + "***********************************************");
        if (user == null) {
            System.out.println("Checking if user is null or what!");
            throw new UsernameNotFoundException("Invalid name or pass");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));

    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
