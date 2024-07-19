package com.machines.machines_api.models.entity;

import com.machines.machines_api.models.baseEntity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "regions")
public class Region extends BaseEntity {
    @Column(nullable = false)
    @NotBlank(message = "Region name can't be blank")
    private String name;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @OneToMany(mappedBy = "region")
    private List<City> cities;
}
