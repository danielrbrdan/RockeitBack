package com.challenge.rockeit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.challenge.rockeit.models.dto.PasswordDTO;
import com.challenge.rockeit.models.entity.Appointment;
import com.challenge.rockeit.models.entity.Patient;
import com.challenge.rockeit.models.entity.User;
import com.challenge.rockeit.repository.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository repository;
    private final PasswordEncoder encoder;


    public Optional<User> findByLogin(String login) {
        return repository.findByLogin(login);
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public Object save(User user) {
        return repository.save(user);
    }

    public Boolean changePassword(PasswordDTO passwordDTO) {
        String login = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();

        Optional<User> userOpt = repository.findByLogin(login);
        
        if (userOpt.isEmpty()) {
            return Boolean.FALSE;
        }

        User user = userOpt.get();
        
        if (!encoder.matches( passwordDTO.getCurrentPassword(), user.getPassword())) {
            return Boolean.FALSE;
        }
        
        String encodedPassword = encoder.encode(passwordDTO.getNewPassword());
        user.setPassword(encodedPassword);
        repository.save(user);
        return Boolean.TRUE;
    }
    
}
