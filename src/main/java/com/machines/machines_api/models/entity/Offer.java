package com.machines.machines_api.models.entity;

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

    private String websiteURL;

    @NotNull(message = "Цената е задължителна")
    @Min(value = 1, message = "Цената трябва да е над 1лв.")
    private double price;

    @NotNull(message = "Задължително е да се знае дали е произведено в България")
    private boolean isBulgarian;

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
    private Set<File> pictures;

    @NotNull(message = "Създателят на обявата е задължителен")
    @ManyToOne
    private User owner;

    // *********************************
    // **   EXTRA INFO | EXTRA INFO   **
    // *********************************
//    @Min(value = 1950, message = "Годината трябва да е след 1950")
    private int manufactureYear;

    //    @NotBlank(message = "Моделът не може да бъде празен")
    private String model;

    //    @DecimalMin(value = "0.1", message = "Захранващото напрежение трябва да е над 0.1")
    private double powerSupplyVoltage;

    //    @NotBlank(message = "Видът гориво не може да бъде празен")
    private String fuelType;

    //    @DecimalMin(value = "0.1", message = "Конската мощност трябва да е над 0.1")
    private double horsePower;

    //    @NotBlank(message = "Консумацията не може да бъде празна")
    private String consumption;

    //    @DecimalMin(value = "0.1", message = "Изходната мощност трябва да е над 0.1")
    private double outputPower;

    //    @NotBlank(message = "Производителността не може да бъде празна")
    private String productivity;

    //    @DecimalMin(value = "0.1", message = "Товароносимостта трябва да е над 0.1")
    private double capacity;

    //    @DecimalMin(value = "0.1", message = "Минималните обороти трябва да са над 0.1")
    private double minRevolutions;

    //    @DecimalMin(value = "0.1", message = "Номиналните обороти трябва да са над 0.1")
    private double nominalRevolutions;

    //    @DecimalMin(value = "0.1", message = "Максималните обороти трябва да са над 0.1")
    private double maxRevolutions;

    //    @NotBlank(message = "Габаритните размери не трябва да са празни")
    private String dimensions;

    //    @DecimalMin(value = "0.1", message = "Собствената маса трябва да е над 0.1")
    private double ownWeight;

    //    @NotBlank(message = "Работните ходове не може да бъдат празни")
    private String workMoves;
}
