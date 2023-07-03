package com.challenge.rockeit.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.rockeit.models.dto.PasswordDTO;
import com.challenge.rockeit.models.dto.PatientDTO;
import com.challenge.rockeit.models.dto.UserDTO;
import com.challenge.rockeit.models.entity.User;
import com.challenge.rockeit.service.UserService;
import com.challenge.rockeit.utils.Mapper;

import lombok.AllArgsConstructor;


@AllArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder encoder;
    private final Mapper mapper;

    @PostMapping()
    public ResponseEntity<PatientDTO> save(@RequestBody User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        PatientDTO patientDTO = mapper.map(userService.save(user), PatientDTO.class);

        return ResponseEntity.ok(patientDTO);
    }

    @GetMapping()
    public ResponseEntity<List<UserDTO>> listAll() {
        List<UserDTO> usersDTO = 
            this.mapper.mapList(userService.findAll(), UserDTO.class);

        return ResponseEntity.ok(usersDTO);
    }

    @GetMapping("/valid_pass")
    public ResponseEntity<Boolean> validPass(@RequestParam String login,
                                                @RequestParam String password) {

        Optional<User> optuser = userService.findByLogin(login);
        if (optuser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
        }

        User user = optuser.get();
        boolean valid = encoder.matches(password, user.getPassword());

        HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
        return ResponseEntity.status(status).body(valid);
    }

    @PutMapping("/password")
    public ResponseEntity<Boolean> changePassword(@RequestBody PasswordDTO passwordDTO) {
        return ResponseEntity.ok(
            userService.changePassword(passwordDTO)
        );
    }
}
