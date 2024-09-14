package com.machines.machines_api.models.entity;


import com.machines.machines_api.models.baseEntity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "searches")
public class Search extends BaseEntity {
    private String text;
}
