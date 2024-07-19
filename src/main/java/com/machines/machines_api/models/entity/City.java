package com.machines.machines_api.models.entity;

import com.machines.machines_api.models.baseEntity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "cities")
public class City extends BaseEntity {
    @Column(nullable = false)
    @NotBlank(message = "City name can't be blank")
    private String name;

    @ManyToOne
    @JoinColumn(name = "region_id", nullable = false)
    private Region region;
}
