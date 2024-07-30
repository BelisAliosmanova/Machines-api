package com.machines.machines_api.models.entity;

import com.machines.machines_api.annotations.OptionalDecimalMin;
import com.machines.machines_api.annotations.OptionalMin;
import com.machines.machines_api.annotations.OptionalNotBlank;
import com.machines.machines_api.enums.OfferSaleType;
import com.machines.machines_api.enums.OfferState;
import com.machines.machines_api.enums.OfferType;
import com.machines.machines_api.models.baseEntity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
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
@Table(name = "offers")
public class Offer extends BaseEntity {
    @NotNull(message = "Заглавието е задължително")
    @NotBlank(message = "Заглавието е задължително")
    private String title;

    @NotNull(message = "Телефонният номер е задължителен")
    @NotBlank(message = "Телефонният номер е задължителен")
    private String phoneNumber;

    @NotNull(message = "Описанието е задължително")
    @NotBlank(message = "Описанието е задължително")
    private String description;

    @Column(name = "website_url")
    private String websiteURL;

    @NotNull(message = "Цената е задължителна")
    @Min(value = 1, message = "Цената трябва да е над 1лв.")
    private double price;

    @NotNull(message = "Задължително е да се знае дали е произведено в България")
    private boolean bulgarian;

    @NotNull(message = "Задължително е да се знае дали да се поднобява обявата автоматично")
    private boolean autoUpdate = false;

    @NotNull(message = "Състоянието е задължително")
    @Enumerated(EnumType.STRING)
    private OfferState offerState;

    @NotNull(message = "Типът на обявата е задължителен")
    @Enumerated(EnumType.STRING)
    private OfferSaleType offerSaleType;

    @NotNull(message = "Вида на обявата е задължителен")
    @Enumerated(EnumType.STRING)
    private OfferType offerType = OfferType.BASIC;

    @NotNull(message = "Градът на обявата е задължителен")
    @ManyToOne
    private City city;

    @NotNull(message = "Подкатегория за обявата е задължителна")
    @ManyToOne
    private Subcategory subcategory;

    @NotNull(message = "Главна снимка за обявата е задължителна")
    @ManyToOne
    private File mainPicture;

    @NotNull(message = "Снимки за обявата са задължителни")
    @Size(min = 1, message = "Поне една снимка за обявата е задължителна")
    @ManyToMany
    @JoinTable(
            name = "offers_pictures",
            joinColumns = @JoinColumn(name = "offer_id"),
            inverseJoinColumns = @JoinColumn(name = "file_id")
    )
    private Set<File> pictures;

    @NotNull(message = "Създателят на обявата е задължителен")
    @ManyToOne
    private User owner;

    // *********************************
    // **   EXTRA INFO | EXTRA INFO   **
    // *********************************
    private Integer manufactureYear;
    private String model;
    private Double powerSupplyVoltage;
    private String fuelType;
    private Double horsePower;
    private String consumption;
    private double outputPower;
    private String productivity;
    private double capacity;
    private double minRevolutions;
    private double nominalRevolutions;
    private double maxRevolutions;
    private String dimensions;
    private double ownWeight;
    private String workMoves;
}

