package com.machines.machines_api.repositories;

import com.machines.machines_api.models.entity.Token;
import com.machines.machines_api.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {
    List<Token> findAllByUser(User user);

    Optional<Token> findByToken(String token);
}
