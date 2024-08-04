package com.machines.machines_api.controllers;

import com.machines.machines_api.models.dto.auth.AdminUserDTO;
import com.machines.machines_api.models.dto.auth.PublicUserDTO;
import com.machines.machines_api.security.filters.JwtAuthenticationFilter;
import com.machines.machines_api.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<AdminUserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}/admin")
    public ResponseEntity<AdminUserDTO> getByIdAdmin(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.getByIdAdmin(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AdminUserDTO> update(@PathVariable("id") UUID id, @RequestBody AdminUserDTO userDTO, HttpServletRequest httpServletRequest) {
        PublicUserDTO user = (PublicUserDTO) httpServletRequest.getAttribute(JwtAuthenticationFilter.USER_KEY);
        return ResponseEntity.ok(userService.updateUser(id, userDTO, user));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") UUID id, HttpServletRequest httpServletRequest) {
        PublicUserDTO user = (PublicUserDTO) httpServletRequest.getAttribute(JwtAuthenticationFilter.USER_KEY);
        userService.deleteUserById(id, user);
        return ResponseEntity.ok().build();
    }
}
