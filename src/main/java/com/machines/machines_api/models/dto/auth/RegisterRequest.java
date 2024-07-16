package com.machines.machines_api.models.dto.auth;


import com.machines.machines_api.enums.Provider;
import com.machines.machines_api.enums.Role;
import com.machines.machines_api.models.dto.request.CompleteOAuthRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest extends CompleteOAuthRequest {
    private String email;
    private String password;
    private Provider provider = Provider.LOCAL;
    private Role role = Role.USER;
}
