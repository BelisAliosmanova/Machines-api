package com.machines.machines_api.services;

import com.machines.machines_api.models.dto.auth.AdminUserDTO;
import com.machines.machines_api.models.dto.auth.PublicUserDTO;
import com.machines.machines_api.models.dto.auth.RegisterRequest;
import com.machines.machines_api.models.entity.User;
import com.machines.machines_api.models.entity.VerificationToken;
import com.machines.machines_api.security.CustomOAuth2User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    User createUser(RegisterRequest request);

    User findByEmail(String email);

    List<AdminUserDTO> getAllUsers();

    AdminUserDTO updateUser(UUID id, AdminUserDTO userDTO, PublicUserDTO currentUser);

    void deleteUserById(UUID id, PublicUserDTO currentUser);

    User processOAuthUser(CustomOAuth2User oAuth2User);

    User findById(UUID id);
}
