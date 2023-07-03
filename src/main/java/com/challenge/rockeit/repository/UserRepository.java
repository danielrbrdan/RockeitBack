package com.challenge.rockeit.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.challenge.rockeit.models.entity.User;


public interface UserRepository extends JpaRepository<User, Long> {

    public Optional<User> findByLogin(String login);

}
