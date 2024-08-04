package com.machines.machines_api.services;

import com.machines.machines_api.models.dto.auth.AdminUserDTO;
import com.machines.machines_api.models.dto.auth.OAuth2UserInfoDTO;
import com.machines.machines_api.models.dto.auth.PublicUserDTO;
import com.machines.machines_api.models.dto.auth.RegisterRequest;
import com.machines.machines_api.models.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    User createUser(RegisterRequest request);

    User findByEmail(String email);

    List<AdminUserDTO> getAllUsers();

    AdminUserDTO getByIdAdmin(UUID id);

    AdminUserDTO updateUser(UUID id, AdminUserDTO userDTO, PublicUserDTO currentUser);

    void deleteUserById(UUID id, PublicUserDTO currentUser);

    User processOAuthUser(OAuth2UserInfoDTO oAuth2User);

    User findById(UUID id);
}
