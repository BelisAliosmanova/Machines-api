package com.machines.machines_api.models.entity;

import com.machines.machines_api.models.baseEntity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
@Table(name = "countries")
public class Country extends BaseEntity {
    @Column(nullable = false)
    @NotBlank(message = "Country name can't be blank")
    private String name;

    @OneToMany(mappedBy = "country")
    private List<Region> regions;
}
