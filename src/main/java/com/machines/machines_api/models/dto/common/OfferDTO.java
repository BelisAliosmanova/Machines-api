package com.machines.machines_api.models.dto.common;

import com.machines.machines_api.annotations.OptionalDecimalMin;
import com.machines.machines_api.annotations.OptionalMin;
import com.machines.machines_api.annotations.OptionalNotBlank;
import com.machines.machines_api.enums.OfferSaleType;
import com.machines.machines_api.enums.OfferState;
import com.machines.machines_api.enums.OfferType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
public class OfferDTO extends BaseDTO {
    private UUID id;
    private String title;
    private String phoneNumber;
    private String description;
    private String websiteURL;
    private double price;
    private boolean bulgarian;
    private boolean autoUpdate = false;
    private OfferState offerState;
    private OfferSaleType offerSaleType;
    private OfferType offerType = OfferType.BASIC;

    // *********************************
    // **   EXTRA INFO | EXTRA INFO   **
    // *********************************
    @OptionalMin(value = 1950, message = "Годината трябва да е след 1950")
    private Integer manufactureYear;

    @OptionalNotBlank(message = "Моделът не може да бъде празен")
    private String model;

    @OptionalDecimalMin(value = 0.1, message = "Захранващото напрежение трябва да е над 0.1")
    private Double powerSupplyVoltage;

    @OptionalNotBlank(message = "Видът гориво не може да бъде празен")
    private String fuelType;

    @OptionalDecimalMin(value = 0.1, message = "Конската мощност трябва да е над 0.1")
    private Double horsePower;

    @OptionalNotBlank(message = "Консумацията не може да бъде празна")
    private String consumption;

    @OptionalDecimalMin(value = 0.1, message = "Изходната мощност трябва да е над 0.1")
    private Double outputPower;

    @OptionalNotBlank(message = "Производителността не може да бъде празна")
    private String productivity;

    @OptionalDecimalMin(value = 0.1, message = "Товароносимостта трябва да е над 0.1")
    private Double capacity;

    @OptionalDecimalMin(value = 0.1, message = "Минималните обороти трябва да са над 0.1")
    private Double minRevolutions;

    @OptionalDecimalMin(value = 0.1, message = "Номиналните обороти трябва да са над 0.1")
    private Double nominalRevolutions;

    @OptionalDecimalMin(value = 0.1, message = "Максималните обороти трябва да са над 0.1")
    private Double maxRevolutions;

    @OptionalNotBlank(message = "Габаритните размери не трябва да са празни")
    private String dimensions;

    @OptionalDecimalMin(value = 0.1, message = "Собствената маса трябва да е над 0.1")
    private Double ownWeight;

    @OptionalNotBlank(message = "Работните ходове не може да бъдат празни")
    private String workMoves;
}
