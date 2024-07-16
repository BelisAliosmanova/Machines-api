package com.machines.machines_api.models.dto.auth;


import com.machines.machines_api.enums.Provider;
import com.machines.machines_api.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String email;
    private String password;
    private String name = "CHANGE_NAME";
    private Role role = Role.USER;
    private Provider provider = Provider.LOCAL;
}
