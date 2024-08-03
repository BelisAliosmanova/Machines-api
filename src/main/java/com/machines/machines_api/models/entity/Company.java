package com.machines.machines_api.models.entity;

import com.machines.machines_api.models.baseEntity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "companies")
public class Company extends BaseEntity {
    @NotNull(message = "Името е задължително")
    @NotBlank(message = "Името е задължително")
    private String name;

    @NotNull(message = "Собственикът е задължителен")
    @ManyToOne
    private User owner;

    private String eik;

    @NotNull(message = "Телефонният номер е задължителен!")
    private String phoneNumber;

    private String fax;

    @NotNull(message = "Градът на фирмата е задължителен!")
    @ManyToOne
    private City city;

    @NotNull(message = "Адресът на фирмата е задължителен!")
    private String address;

    private String website;

    @NotNull(message = "Описанието е задължително")
    @NotBlank(message = "Описанието е задължително")
    private String description;

    @NotNull(message = "Главна снимка за фирмата е задължителна")
    @ManyToOne
    private File mainPicture;

    @NotNull(message = "Снимки за фирмата са задължителни")
    @Size(min = 1, message = "Поне една снимка за фирма е задължителна")
    @ManyToMany
    @JoinTable(
            name = "companies_pictures",
            joinColumns = @JoinColumn(name = "company_id"),
            inverseJoinColumns = @JoinColumn(name = "file_id")
    )
    private Set<File> pictures;
}