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
@Table(name = "subcategories")
public class Subcategory extends BaseEntity {
    @Column(nullable = false)
    @NotBlank(message = "The subcategory cannot be blank.")
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category mainCategory;
}
