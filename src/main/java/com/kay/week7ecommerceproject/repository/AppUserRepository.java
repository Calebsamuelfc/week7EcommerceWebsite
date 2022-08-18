package com.kay.week7ecommerceproject.repository;

import com.kay.week7ecommerceproject.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findAppUserByEmail(String email);
    Optional<AppUser> findAppUserByEmailAndPassword(String email, String password);
}
