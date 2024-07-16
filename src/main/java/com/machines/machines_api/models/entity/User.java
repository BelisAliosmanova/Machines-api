package com.machines.machines_api.models.entity;

import com.machines.machines_api.enums.Provider;
import com.machines.machines_api.enums.Role;
import com.machines.machines_api.models.baseEntity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User extends BaseEntity implements UserDetails {

    @NotNull(message = "The name of the user should not be null!")
    @Size(min = 2, message = "The name should be at least 2 symbols!")
    private String name;

    @NotNull(message = "The surname should not be null!")
    @Size(min = 2, message = "The surname should be at least 2 symbols!")
    private String surname;

    @Email(message = "Email should be a well-formatted email!")
    @NotNull(message = "The email should not be null!")
    @Column(unique = true)
    private String email;

    @NotNull(message = "The password should not be null!")
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Provider provider;

    @Column(name = "is_additional_info_required", nullable = false)
    private boolean additionalInfoRequired;

    @Column(name = "enabled")
    private boolean enabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @PrePersist
    void prePersist() {
        if (this.provider == null) {
            this.provider = Provider.LOCAL;
        }
    }
}
