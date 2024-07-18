package com.machines.machines_api.models.entity;

import com.machines.machines_api.models.baseEntity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "categories")
public class Category extends BaseEntity {
    @Column(nullable = false)
    @NotBlank(message = "The category cannot be blank.")
    private String name;

    @OneToMany(mappedBy = "mainCategory")
    private List<Subcategory> subcategories;
}
